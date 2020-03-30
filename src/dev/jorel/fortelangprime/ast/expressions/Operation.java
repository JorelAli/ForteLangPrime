package dev.jorel.fortelangprime.ast.expressions;

import static dev.jorel.fortelangprime.parser.ForteLangPrimeParserConstants.EQUALS_EQUALS;
import static dev.jorel.fortelangprime.parser.ForteLangPrimeParserConstants.CUSTOM_OPERATOR;

import java.util.Arrays;
import java.util.Optional;

public class Operation {

	public static final Operation EQUALS = new Operation(0, EQUALS_EQUALS);
	
	public static final Operation[] VALUES = new Operation[] {
		EQUALS
	};
	
	public final int ID;
	private int kind;
	
	public Operation(int id, int kind) {
		this.ID = id;
		this.kind = kind;
	}
	
	private static Operation customOperator() {
		return new Operation(-1, 0);
	}
	
	public static Operation from(int kind) {
		
		if(kind == CUSTOM_OPERATOR) {
			return customOperator();
		}
		
		Optional<Operation> foundOperation = Arrays.stream(Operation.VALUES)
			.filter(k -> k.kind == kind).findFirst();
		
		if(foundOperation.isPresent()) {
			return foundOperation.get();
		} else {
			//TODO: Throw error, could not find operation
			return null;
		}
	}
	
}
