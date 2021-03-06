package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeString;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * String literals. e.g. "hello"
 */
public class ExprStringLit implements Expr {
	
	private int lineNumber;
	private String value;
	
	public ExprStringLit(int lineNumber, String value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
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
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		FLPCompiler.log("Emitting String value " + value);
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
