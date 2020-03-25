package dev.jorel.fortelangprime.ast.expressions;

import dev.jorel.fortelangprime.ast.CodeableMethod;
import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public interface Expr extends CodeableMethod {

	public Type getType(TypingContext context) throws TypeException;
	public boolean isReducable();
	public Expr substitute(String name, Expr val);
	public Expr deepCopy();
	public ExpressionType getInternalType();
	
}
