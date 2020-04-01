package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprInternalCast implements Expr {

	private final int castOpcode;
	
	public ExprInternalCast(int castOpcode) {
		this.castOpcode = castOpcode;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		methodVisitor.visitInsn(castOpcode);
	}
	
	@Override
	public int getLineNumber() {
		throw new RuntimeException("ExprInternalCast line number invalid");
	}

	@Override
	public int returnType(UniversalContext context) {
		throw new RuntimeException("ExprInternalCast return type invalid");
	}

	@Override
	public Type getType(UniversalContext context) {
		throw new RuntimeException("ExprInternalCast type invalid");
	}

	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		throw new RuntimeException("ExprInternalCast type check invalid");
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expr substitute(String name, Expr val) {
		return null;
	}

	@Override
	public Expr deepCopy() {
		return null;
	}

	@Override
	public ExpressionType getInternalType() {
		throw new RuntimeException("ExprInternalCast internal type invalid");
	}

}
