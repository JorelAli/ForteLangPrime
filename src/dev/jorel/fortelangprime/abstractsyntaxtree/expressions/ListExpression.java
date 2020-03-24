package dev.jorel.fortelangprime.abstractsyntaxtree.expressions;

import java.util.List;

import dev.jorel.fortelangprime.abstractsyntaxtree.enums.ExpressionType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.ListType;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.Type;
import dev.jorel.fortelangprime.abstractsyntaxtree.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ListExpression implements Expression {
	
	private List<Expression> elements;

	@Override
	public Type getType(TypingContext context) throws TypeException {
		if(elements.size() == 0) {
			// TODO Auto-generated method stub
			return null;
		} else {
			//TODO: Check that each type is of the same type
			return new ListType(elements.get(0).getType(context));
		}
	}

	@Override
	public boolean isReducable() {
		return false; // Or can you?
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
		return ExpressionType.LIST;
	}

}
