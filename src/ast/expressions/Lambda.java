package ast.expressions;

import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

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

}
