package dev.jorel.fortelangprime.ast.expressions;

import java.util.Arrays;

import dev.jorel.fortelangprime.parser.ForteLangPrimeParserConstants;

public enum Operation {

	EQUALS_EQUALS(ForteLangPrimeParserConstants.EQUALS_EQUALS);
	
	private int kind;
	
	Operation(int kind) {
		this.kind = kind;
	}
	
	public static Operation from(int kind) {
		return Arrays.stream(Operation.values()).filter(k -> k.kind == kind).findFirst().get();
	}
	
}
