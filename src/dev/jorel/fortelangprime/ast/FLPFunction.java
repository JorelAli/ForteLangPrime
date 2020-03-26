package dev.jorel.fortelangprime.ast;

import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

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
	public void emit(ClassWriter classWriter, TypingContext context) {
		
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
		
		MethodVisitor methodVisitor = classWriter.visitMethod((exported ? ACC_PUBLIC : ACC_PRIVATE) | ACC_STATIC, name, typeFunction.toBytecodeString(), genericSignature, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(body.getLineNumber(), lineNumber);
		
		body.emit(methodVisitor, context);
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
