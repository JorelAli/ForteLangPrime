package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprPanic implements Expr {
	
	private int lineNumber;
	
	public ExprPanic(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return new TypeBool();
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expr substitute(String name, Expr val) {
		return this;
	}

	@Override
	public Expr deepCopy() {
		return new ExprPanic(lineNumber);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.PANIC;
	}

	@Override
	public void emit(MethodVisitor methodVisitor) {
		methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
		methodVisitor.visitInsn(DUP);
		methodVisitor.visitLdcInsn("Panic operator reached");
		methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V", false);
	}

	@Override
	public int returnType() {
		return ATHROW;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
