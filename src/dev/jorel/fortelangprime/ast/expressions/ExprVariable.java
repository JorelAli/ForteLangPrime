package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.parser.util.Pair;

public class ExprVariable implements Expr {
	
	private int lineNumber;
	private String name;
	private String parentFunctionName;
	
	public ExprVariable(int lineNumber, String name, String parentFunctionName) {
		this.lineNumber = lineNumber;
		this.name = name;
		this.parentFunctionName = parentFunctionName;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return context.getFunction(parentFunctionName).getParams().stream()
			.filter(p -> p.first().equals(name))
			.findFirst().get().second();
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
		return new ExprVariable(lineNumber, name, parentFunctionName);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.VARIABLE;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, TypingContext context) {
		int index = 0;
		for(Pair<String, Type> pair : context.getFunction(parentFunctionName).getParams()) {
			if(pair.first().equals(name)) {
				break;
			} else {
				index++;
			}
		}
		Type paramType = getTypeUnsafe(context);
		methodVisitor.visitVarInsn(paramType.loadInstruction(), index);
	}
	
	private Type getTypeUnsafe(TypingContext context) {
		return context.getFunction(parentFunctionName).getParams().stream()
				.filter(p -> p.first().equals(name))
				.findFirst().get().second();
	}

	@Override
	public int returnType(TypingContext context) {
		return getTypeUnsafe(context).returnType();
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
