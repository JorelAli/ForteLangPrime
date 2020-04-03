package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * A boolean literal (true or false)
 */
public class ExprBoolLit implements Expr {
	
	private int lineNumber;
	private boolean value;
	
	public ExprBoolLit(int lineNumber, boolean value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	
	public boolean getValue() {
		return this.value;
	}	

	@Override
	public Type getType(UniversalContext context) {
		return new TypeBool();
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
		return new ExprBoolLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.BOOL_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		if(value) {
			FLPCompiler.log("Emitting boolean value true");
			methodVisitor.visitInsn(ICONST_1);
		} else {
			FLPCompiler.log("Emitting boolean value false");
			methodVisitor.visitInsn(ICONST_0);
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		return IRETURN;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
