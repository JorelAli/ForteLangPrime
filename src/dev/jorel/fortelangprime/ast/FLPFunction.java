package dev.jorel.fortelangprime.ast;

import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypeNamedGeneric;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.util.Pair;
import dev.jorel.fortelangprime.util.StreamUtils;

public class FLPFunction implements CodeableClass {

	private final int lineNumber;
	private final String name;
	private final TypeFunction typeFunction;
	private final List<TypeNamedGeneric> genericDeclarations;
	private final Expr body;
	private boolean exported;
	
	public FLPFunction(int lineNumber, String name, TypeFunction typeFunction, List<TypeNamedGeneric> genericDeclarations, Expr body) {
		this.lineNumber = lineNumber;
		this.name = name;
		this.typeFunction = typeFunction;
		this.genericDeclarations = genericDeclarations;
		this.body = body;
		this.exported = false;
	}
	
	public void setPublic() {
		this.exported = true;
	}
	
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	public Expr getFunctionBody() {
		return this.body;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void emit(ClassWriter classWriter, UniversalContext context) {
		
		String genericSignature;
		if(genericDeclarations.size() == 0) {
			genericSignature = null;
		} else {
			StringBuilder builder = new StringBuilder("<");
			for(TypeNamedGeneric generic : genericDeclarations) {
				builder.append(generic.getName());
				builder.append(":");
				builder.append(generic.toBytecodeString(context));
			}
			builder.append(">");
			builder.append(typeFunction.toGenericBytecodeString(context));
			genericSignature = builder.toString();
		}
		
		String returnTypeString = typeFunction.toBytecodeString(context);
		if(typeFunction.getReturnType(context) instanceof TypeNamedGeneric) {
			String s = ((TypeNamedGeneric) typeFunction.getReturnType(context)).getName();
			if(context.getRecordType(s) != null) {
				
				StringBuilder result = new StringBuilder("(");
				typeFunction.getParams(context).stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).forEach(result::append);
				result.append(")");
				result.append(context.getRecordType(s).toBytecodeString(context));
				returnTypeString = result.toString(); 
			}
		}

		FLPCompiler.log("\nEmitting function " + name + " of type " + returnTypeString);
		MethodVisitor methodVisitor = classWriter.visitMethod((exported ? ACC_PUBLIC : ACC_PRIVATE) | ACC_STATIC, name, returnTypeString, genericSignature, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(body.getLineNumber(), lineNumber);
		
		body.emit(methodVisitor, context);
//		System.out.println(name + body.getInternalType() " -> " + body.returnType(context));
		methodVisitor.visitInsn(body.returnType(context));
		
		Label variableTypes = new Label();
		methodVisitor.visitLabel(variableTypes);
		int index = 0;
		for(Pair<String, Type> e : typeFunction.getParams(context)) {
			if(e.first() == null) {
				index++;
				continue;
			}
			methodVisitor.visitLocalVariable(e.first(), e.second().toBytecodeString(context), e.second().toGenericBytecodeString(context), lineNumber, variableTypes, index++);
		}
		
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}
	
}
