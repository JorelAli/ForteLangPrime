package ast.expressions;

import java.math.BigDecimal;

import ast.enums.ExpressionType;
import ast.types.Type;
import ast.types.TypingContext;
import exceptions.TypeException;

public class NumLiteral implements Expression {
	
	private BigDecimal value;
	
	public NumLiteral(String string) {
		this.value = new BigDecimal(string);
	}

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
		return this;
	}

	@Override
	public Expression deepCopy() {
		return new NumLiteral(value.toPlainString());
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.NUM_LITERAL;
	}

}
