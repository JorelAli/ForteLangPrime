package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeDouble;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprDoubleLit implements Expr {
	
	private int lineNumber;
	private double value;
	
	public ExprDoubleLit(int lineNumber, double value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public Type getType(UniversalContext context) {
		return new TypeDouble();
	}
	
	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		return getType(context);
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
		return new ExprDoubleLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.DOUBLE_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		if(value == 0) {
			methodVisitor.visitInsn(DCONST_0);
		} else if(value == 1) {
			methodVisitor.visitInsn(DCONST_1);
		} else {
			methodVisitor.visitLdcInsn(Double.valueOf(value));
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		return DRETURN;
	}

	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}
