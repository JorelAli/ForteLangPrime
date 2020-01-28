package ast.expressions;

import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

public interface Expression {

	public Type getType(TypingContext context) throws TypeException;
	public boolean isReducable();
	public Expression substitute(String name, Expression val);
	public Expression deepCopy();
	
}
