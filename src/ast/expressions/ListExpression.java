package ast.expressions;

import javax.annotation.Nullable;

import ast.enums.ExpressionType;
import ast.types.ListType;
import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

public class ListExpression implements Expression {
	
	@Nullable private Expression element;
	private ListExpression elements;

	@Override
	public Type getType(TypingContext context) throws TypeException {
		if(element != null) {
			return new ListType(element.getType(context));
		} else {
			// TODO Auto-generated method stub
			return null;
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
