package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.StandardTypes;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class StringLiteral implements Expression {
	
	private String value;
	
	public StringLiteral(String string) {
		this.value = string;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return StandardTypes.StringType();
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expression substitute(String name, Expression val) {
		return this;
	}

	@Override
	public Expression deepCopy() {
		return new StringLiteral(this.value);
	}
	
	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.STRING;
	}

}
