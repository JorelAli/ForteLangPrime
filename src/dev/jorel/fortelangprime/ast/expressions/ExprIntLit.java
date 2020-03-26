package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.TypeInt;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprIntLit implements Expr {
	
	private int lineNumber;
	private int value;
	
	public ExprIntLit(int lineNumber, int value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return new TypeInt();
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
		return new ExprIntLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.INT_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, TypingContext context) {
		switch(value) {
			case -1:
				methodVisitor.visitInsn(ICONST_M1);
				break;
			case 0:
				methodVisitor.visitInsn(ICONST_0);
				break;
			case 1:
				methodVisitor.visitInsn(ICONST_1);
				break;
			case 2:
				methodVisitor.visitInsn(ICONST_2);
				break;
			case 3:
				methodVisitor.visitInsn(ICONST_3);
				break;
			case 4:
				methodVisitor.visitInsn(ICONST_4);
				break;
			case 5:
				methodVisitor.visitInsn(ICONST_5);
				break;
			default:
				methodVisitor.visitIntInsn(BIPUSH, value);
				break;
		}
	}

	@Override
	public int returnType() {
		return IRETURN;
	}

	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}