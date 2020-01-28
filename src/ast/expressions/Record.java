package ast.expressions;

import java.util.Map;

import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

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

}
