package ast.expressions;

import java.util.LinkedHashMap;

import ast.types.StandardTypes;
import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

public class Guards implements Expression {
	
	private LinkedHashMap<Expression, Expression> statements;
	private Expression finalStatement;

	@Override
	public Type getType(TypingContext context) throws TypeException {
		
		Type returnType = finalStatement.getType(context);
		for(Expression expr : statements.values()) {
			if(!expr.getType(context).equals(returnType)) {
				throw new TypeException(); //Must have same return type
			}
		}
		
		for(Expression expr : statements.keySet()) {
			if(!expr.getType(context).equals(StandardTypes.BoolType())) {
				throw new TypeException(); //Conditions must be of type bool
			}
		}
		
		return returnType;
	}

	@Override
	public boolean isReducable() {
		return true;
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
