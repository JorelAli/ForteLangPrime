package ast.expressions;

import ast.enums.ExpressionType;
import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

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
