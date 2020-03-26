package dev.jorel.fortelangprime.ast.types;

import java.util.HashMap;
import java.util.Map;

public class TypingContext {
	
	/*
	 * TODO: A string is not sufficient to uniquely
	 * identify a function. Functions have overloading!
	 */
	Map<String, TypeFunction> functions;
	
	public TypingContext() {
		this.functions = new HashMap<>();
	}
	
	public void addFunction(String functionName, TypeFunction typeFunction) {
		functions.put(functionName, typeFunction);
	}

	public TypeFunction getFunction(String functionName) {
		return functions.get(functionName);
	}

}
