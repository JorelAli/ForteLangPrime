package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprIfStatement implements Expr {
	
	private int lineNumber;
	private Expr condition;
	private Expr ifTrue;
	private Expr ifFalse;
	
	public ExprIfStatement(int lineNumber, Expr condition, Expr ifTrue, Expr ifFalse) {
		this.lineNumber = lineNumber;
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
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
		return new ExprIfStatement(lineNumber, condition, ifTrue, ifFalse);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.IF_STATEMENT;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, TypingContext context) {
//		if(value) {
//			methodVisitor.visitInsn(ICONST_1);
//		} else {
//			methodVisitor.visitInsn(ICONST_0);
//		}
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
