package dev.jorel.fortelangprime.ast.operation;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class UnresolvedCustomOperation implements Operation {
	
	final String operatorToken;
	
	public UnresolvedCustomOperation(String operatorToken) {
		this.operatorToken = operatorToken;
	}

	@Override
	public boolean isStandard() {
		return false;
	}
	
	@Override
	public Operation resolve(UniversalContext context) {
		return context.searchCustomOperation(operatorToken);
	}

	@Override
	public boolean isUnresolved() {
		return true;
	}

	@Override
	public int getPrecedence() {
		return -1;
	}

	@Override
	public Associativity getAssociativity() {
		return null;
	}

}
