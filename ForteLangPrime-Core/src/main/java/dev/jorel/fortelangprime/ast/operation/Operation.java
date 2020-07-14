package dev.jorel.fortelangprime.ast.operation;

import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParserConstants;

public interface Operation extends ShuntingYardable {
	
	public boolean isStandard();
	public boolean isUnresolved();
	public Operation resolve(UniversalContext context);
	public int getPrecedence();
	public Associativity getAssociativity();
	
	public static Operation from(int kind, String image) {
		if(kind == ForteLangPrimeParserConstants.CUSTOM_OPERATOR) {
			return new UnresolvedCustomOperation(image);
		} else {
			return StandardOperation.from(kind);
		}
	}
	
}
