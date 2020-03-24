package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import java.math.BigDecimal;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class NumLiteral implements Expression {
	
	private BigDecimal value;
	
	public NumLiteral(String string) {
		this.value = new BigDecimal(string);
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		// TODO Auto-generated method stub
		return null;
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
		return new NumLiteral(value.toPlainString());
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.NUM_LITERAL;
	}

}
