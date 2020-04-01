package dev.jorel.fortelangprime.ast.operation;

import static dev.jorel.fortelangprime.parser.ForteLangPrimeParserConstants.*;

import java.util.Arrays;
import java.util.Optional;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public enum StandardOperation implements Operation {
	
	POW(HAT, 8, Associativity.RIGHT),
	
	MULTIPLY(STAR, 7, Associativity.LEFT),
	MODULO(MOD, 7, Associativity.LEFT),
	DIVIDE(SLASH, 7, Associativity.LEFT),
	
	SUBTRACT(MINUS, 6, Associativity.LEFT),
	ADD(PLUS, 6, Associativity.LEFT),
	
	CONS(COLON, 5, Associativity.RIGHT),
	CONCATENATE(CONCAT, 5, Associativity.RIGHT),
	
	EQUALS(EQUALS_EQUALS, 4, Associativity.NONE),
	NE(NOT_EQUALS, 4, Associativity.NONE),
	LT(LCHEVRON, 4, Associativity.NONE),
	GT(RCHEVRON, 4, Associativity.NONE),
	GE(GREATER_THAN_OR_EQUAL, 4, Associativity.NONE),
	LE(LESS_THAN_OR_EQUAL, 4, Associativity.NONE),
	
	AND(AND_AND, 3, Associativity.RIGHT),
	
	OR(OR_OR, 2, Associativity.RIGHT),
	
	PIPE2RIGHT(PLAY_BUTTON, 0, Associativity.LEFT),
	PIPE2LEFT(REVERSE_PLAY_BUTTON, 0, Associativity.RIGHT),
	;
	

	
	private int kind;
	private int precedence;
	private Associativity associativity;
	
	StandardOperation(int kind, int precedence, Associativity associativity) {
		this.kind = kind;
		this.precedence = precedence;
		this.associativity = associativity;
	}
	
	public static StandardOperation from(int kind) {
		
		Optional<StandardOperation> foundOperation = Arrays.stream(StandardOperation.values())
			.filter(k -> k.kind == kind).findFirst();
		
		if(foundOperation.isPresent()) {
			return foundOperation.get();
		} else {
			//TODO: Throw error, could not find operation
			return null;
		}
	}

	@Override
	public boolean isStandard() {
		return true;
	}

	@Override
	public boolean isUnresolved() {
		return false;
	}

	@Override
	public Operation resolve(UniversalContext context) {
		return this;
	}

	@Override
	public int getPrecedence() {
		return this.precedence;
	}

	@Override
	public Associativity getAssociativity() {
		return this.associativity;
	}
	
}
