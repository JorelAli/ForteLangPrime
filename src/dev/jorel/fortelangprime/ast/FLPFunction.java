package dev.jorel.fortelangprime.ast;

import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypeNamedGeneric;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.util.Pair;

public class FLPFunction implements CodeableClass {

	final private String name;
	final private TypeFunction typeFunction;
	final private List<TypeNamedGeneric> genericDeclarations;
	final private Expr body;
	private boolean exported;
	
	public FLPFunction(String name, TypeFunction typeFunction, List<TypeNamedGeneric> genericDeclarations, Expr body) {
		this.name = name;
		this.typeFunction = typeFunction;
		this.genericDeclarations = genericDeclarations;
		this.body = body;
		this.exported = false;
	}
	
	public void setPublic() {
		this.exported = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void emit(EmitterContext proj, ClassWriter classWriter, TypingContext context) {
		
		String genericSignature;
		if(genericDeclarations.size() == 0) {
			genericSignature = null;
		} else {
			StringBuilder builder = new StringBuilder("<");
			for(TypeNamedGeneric generic : genericDeclarations) {
				builder.append(generic.getName());
				builder.append(":");
				builder.append(generic.toBytecodeString());
			}
			builder.append(">");
			builder.append(typeFunction.toGenericBytecodeString());
			genericSignature = builder.toString();
		}
		
		String returnTypeString = typeFunction.toBytecodeString();
		if(typeFunction.getReturnType() instanceof TypeNamedGeneric) {
			String s = ((TypeNamedGeneric) typeFunction.getReturnType()).getName();
			if(context.getRecordType(s) != null) {
				
				StringBuilder result = new StringBuilder("(");
				typeFunction.getParams().stream().map(Pair::second).map(Type::toBytecodeString).forEach(result::append);
				result.append(")");
				result.append("L" + proj.getLibraryName() + context.getRecordType(s).toBytecodeString());
				returnTypeString = result.toString(); 
			}
		}
//		System.out.println(typeFunction.getReturnType().getClass().getName());
//		System.out.println("Writing " + name + " : " + returnTypeString);
		MethodVisitor methodVisitor = classWriter.visitMethod((exported ? ACC_PUBLIC : ACC_PRIVATE) | ACC_STATIC, name, returnTypeString, genericSignature, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(body.getLineNumber(), lineNumber);
		
		body.emit(proj, methodVisitor, context);
		methodVisitor.visitInsn(body.returnType(context));
		
		Label variableTypes = new Label();
		methodVisitor.visitLabel(variableTypes);
		int index = 0;
		for(Pair<String, Type> e : typeFunction.getParams()) {
			if(e.first() == null) {
				index++;
				continue;
			}
			methodVisitor.visitLocalVariable(e.first(), e.second().toBytecodeString(), e.second().toGenericBytecodeString(), lineNumber, variableTypes, index++);
		}
		
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}
	
}
