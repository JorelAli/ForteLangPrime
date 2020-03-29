package dev.jorel.fortelangprime.ast.types;

import java.util.HashMap;
import java.util.Map;

public class TypingContext {
	
	Map<String, TypeFunction> functions;
	Map<String, TypeRecord> recordTypes;
	
	public TypingContext() {
		this.functions = new HashMap<>();
		this.recordTypes = new HashMap<>();
	}
	
	public void addFunction(String functionName, TypeFunction typeFunction) {
		functions.put(functionName, typeFunction);
	}

	public TypeFunction getFunction(String functionName) {
		return functions.get(functionName);
	}
	
	public void addRecordType(String recordName, TypeRecord type) {
		this.recordTypes.put(recordName, type);
	}

}
