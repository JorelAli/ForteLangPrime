package dev.jorel.fortelangprime.ast.expressions;

import dev.jorel.fortelangprime.ast.CodeableMethod;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public interface Expr extends CodeableMethod {

	// Type system
	public Type getType(UniversalContext context);
	
	/**
	 * Used to type check an expression. If well typed, returns
	 * the type of the expression (as defined by the getType(context))
	 * method. Otherwise, throws a TypeException
	 * @param context
	 * @return
	 * @throws TypeException
	 */
	public Type typeCheck(UniversalContext context) throws TypeException; 
	
	public boolean isReducable();
	public Expr substitute(String name, Expr val);
	public Expr deepCopy();
	public ExpressionType getInternalType();
	
}
