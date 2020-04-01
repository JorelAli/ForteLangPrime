package dev.jorel.fortelangprime.ast.operation;

import static dev.jorel.fortelangprime.parser.ForteLangPrimeParserConstants.EQUALS_EQUALS;

import java.util.Arrays;
import java.util.Optional;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public enum StandardOperation implements Operation {
	
	EQUALS(EQUALS_EQUALS);
	
	private int kind;
	
	StandardOperation(int kind) {
		this.kind = kind;
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
	
}
