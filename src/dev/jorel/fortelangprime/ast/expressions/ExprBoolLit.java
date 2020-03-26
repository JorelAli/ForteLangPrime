package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprBoolLit implements Expr {
	
	private int lineNumber;
	private boolean value;
	
	public ExprBoolLit(int lineNumber, boolean value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}
	
	public boolean getValue() {
		return this.value;
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
		return new ExprBoolLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.BOOL_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, TypingContext context) {
		if(value) {
			methodVisitor.visitInsn(ICONST_1);
		} else {
			methodVisitor.visitInsn(ICONST_0);
		}
	}

	@Override
	public int returnType(TypingContext context) {
		return IRETURN;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
