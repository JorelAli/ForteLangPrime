package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * The panic operator (like undefined in Haskell)
 */
public class ExprPanic implements Expr {
	
	private int lineNumber;
	
	public ExprPanic(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public Type getType(UniversalContext context) {
		return null; //TODO: ??
	}

	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		return null; //TODO: ??
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
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		FLPCompiler.log("Emitting panic operation (throws runtime exception)");
		methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
		methodVisitor.visitInsn(DUP);
		methodVisitor.visitLdcInsn("Panic operator reached");
		methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V", false);
	}

	@Override
	public int returnType(UniversalContext context) {
		return ATHROW;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
