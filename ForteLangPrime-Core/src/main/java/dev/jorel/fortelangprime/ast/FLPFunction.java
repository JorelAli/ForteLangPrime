package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.util.Pair;

public class FLPFunction implements CodeableClass {

	private final int lineNumber;
	private final String name;
	private final TypeFunction typeFunction;
	private final Expr body;
	private boolean exported;
	
	public FLPFunction(int lineNumber, String name, TypeFunction typeFunction, Expr body) {
		this.lineNumber = lineNumber;
		this.name = name;
		this.typeFunction = typeFunction;
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
		
		String genericSignature = typeFunction.toGenericBytecodeString(context);
//		if(genericDeclarations.size() == 0) {
//			genericSignature = null;
//		} else {
//			StringBuilder builder = new StringBuilder("<");
//			for(TypeGeneric generic : genericDeclarations) {
//				builder.append(generic.getName());
//				builder.append(":");
//				builder.append(generic.toBytecodeString(context));
//			}
//			builder.append(">");
//			builder.append(typeFunction.toGenericBytecodeString(context));
//			genericSignature = builder.toString();
//		}
		
		String returnTypeString = typeFunction.toBytecodeString(context);

		FLPCompiler.log("\nEmitting function " + name + " of type " + returnTypeString);
		FLPCompiler.log("Using generic type function: " + typeFunction.toGenericBytecodeString(context));
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
