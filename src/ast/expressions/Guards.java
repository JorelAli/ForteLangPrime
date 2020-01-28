package ast.expressions;

import java.util.LinkedHashMap;

import ast.enums.ExpressionType;
import ast.types.StandardTypes;
import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

public class Guards implements Expression {
	
	private LinkedHashMap<Expression, Expression> statements;
	private Expression finalStatement;
	
	public Guards(LinkedHashMap<Expression, Expression> statements, Expression finalStatement) {
		this.statements = statements;
		this.finalStatement = finalStatement;
	}
	
	public static Guards fromIfStatement(Expression ifExpr, Expression thenExpr, Expression elseExpr) {
		LinkedHashMap<Expression, Expression> statement = new LinkedHashMap<>();
		statement.put(ifExpr, thenExpr);
		return new Guards(statement, elseExpr);
	}

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
//		for(Statement)
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression deepCopy() {
		return new Guards(new LinkedHashMap<Expression, Expression>(statements), finalStatement.deepCopy());
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.GUARDS;
	}

}
