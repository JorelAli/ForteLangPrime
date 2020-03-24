package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public interface Expression {

	public Type getType(TypingContext context) throws TypeException;
	public boolean isReducable();
	public Expression substitute(String name, Expression val);
	public Expression deepCopy();
	public ExpressionType getInternalType();
	
}
