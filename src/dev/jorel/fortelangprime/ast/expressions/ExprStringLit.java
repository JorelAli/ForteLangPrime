package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeString;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprStringLit implements Expr {
	
	private int lineNumber;
	private String value;
	
	public ExprStringLit(int lineNumber, String value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}

	@Override
	public Type getType(UniversalContext context) {
		return new TypeString();
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
		return new ExprStringLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.STRING_LITERAL;
	}

	@Override
	public void emit(EmitterContext prog, MethodVisitor methodVisitor, UniversalContext context) {
		methodVisitor.visitLdcInsn(value);
	}

	@Override
	public int returnType(UniversalContext context) {
		return ARETURN;
	}
	
	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}
