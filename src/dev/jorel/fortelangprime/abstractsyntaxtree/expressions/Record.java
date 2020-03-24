package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import java.util.Map;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class Record implements Expression {

	Map<String, Expression> definitions;
	
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
		return ExpressionType.RECORD;
	}

}
