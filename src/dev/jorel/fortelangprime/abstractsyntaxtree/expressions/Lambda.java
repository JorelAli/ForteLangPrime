package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class Lambda implements Expression {

	@Override
	public Type getType(TypingContext context) throws TypeException {
		TypingContext newContext = new TypingContext();
		newContext.putAll(context);
		//Etc. etc.
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expression substitute(String name, Expression val) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.LAMBDA;
	}

}
