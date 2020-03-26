package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprFunctionCall implements Expr {
	
	private int lineNumber;
	private String functionName;
	private String fileName;
	private Type functionType;
	
	public ExprFunctionCall(int lineNumber, String functionName) {
		this.lineNumber = lineNumber;
		this.functionName = functionName;
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
		return new ExprFunctionCall(lineNumber, functionName);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.FUNCTION_CALL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor) {
		methodVisitor.visitMethodInsn(INVOKESTATIC, fileName, functionName, functionType.toBytecodeString(), true);
	}

	@Override
	public int returnType() {
		return ARETURN;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
