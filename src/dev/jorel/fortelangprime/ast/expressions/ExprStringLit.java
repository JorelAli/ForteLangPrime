package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.TypeString;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprStringLit implements Expr {
	
	private int lineNumber;
	private String value;
	
	public ExprStringLit(int lineNumber, String value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return new TypeString();
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
		return new ExprStringLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.STRING_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor) {
		methodVisitor.visitLdcInsn(value);
	}

	@Override
	public int returnType() {
		return ARETURN;
	}
	
	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}
