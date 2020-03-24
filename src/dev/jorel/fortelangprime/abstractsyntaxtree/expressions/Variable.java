package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class Variable implements Expression {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return context.get(name);
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expression substitute(String name, Expression val) {
		if(name.equals(this.name)) {
			return val.deepCopy();
		} else {
			return this;
		}
	}

	@Override
	public Expression deepCopy() {
		return new Variable(this.name);
	}
	
	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.VARIABLE;
	}

}
