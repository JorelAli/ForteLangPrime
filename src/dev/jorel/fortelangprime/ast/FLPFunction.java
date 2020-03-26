package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.TypeFunction;

public class FLPFunction implements CodeableClass {

	final String name;
	final TypeFunction typeFunction;
	final Expr body;
	
	public FLPFunction(String name, TypeFunction typeFunction, Expr body) {
		this.name = name;
		this.typeFunction = typeFunction;
		this.body = body;
	}
	
	@Override
	public void emit(ClassWriter classWriter) {
		MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, name, typeFunction.toBytecodeString(), null, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(body.getLineNumber(), lineNumber);
		
		body.emit(methodVisitor);
		methodVisitor.visitInsn(body.returnType());
		
//		Label variableTypes = new Label();
//		methodVisitor.visitLabel(variableTypes);
//		methodVisitor.visitLocalVariable("this", "Ldev/jorel/fortelangprime/ast/FLPFunction;", null, lineNumber, variableTypes, 0);
//		methodVisitor.visitLocalVariable("classWriter", "Lorg/objectweb/asm/ClassWriter;", null, label0, label10, 1);
//		methodVisitor.visitLocalVariable("methodVisitor", "Lorg/objectweb/asm/MethodVisitor;", null, label1, label10, 2);
//		methodVisitor.visitLocalVariable("lineNumber", "Lorg/objectweb/asm/Label;", null, label3, label10, 3);
		
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}
	
}
