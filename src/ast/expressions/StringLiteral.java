package ast.expressions;

import ast.types.StandardTypes;
import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

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

}
