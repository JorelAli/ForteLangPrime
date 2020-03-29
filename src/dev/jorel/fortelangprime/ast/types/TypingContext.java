package dev.jorel.fortelangprime.ast.types;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import dev.jorel.fortelangprime.parser.util.Pair;

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
	
	public Type getRecordTypeMatching(List<Pair<String, Type>> types) {
		//TODO: This is currently a trash implementation just for testing for now
		for(Entry<String, TypeRecord> entry : recordTypes.entrySet()) {
			Set<String> keysInContext = entry.getValue().getTypes().stream().map(Pair::first).collect(Collectors.toSet());
			Set<String> keysInCheck = types.stream().map(Pair::first).collect(Collectors.toSet());
			if(keysInCheck.equals(keysInContext)) {
				return entry.getValue();
			}
		}
		return null;
	}
	
	public String getNameOfRecordTypeMatching(List<Pair<String, Type>> types) {
		//TODO: This is currently a trash implementation just for testing for now
		for(Entry<String, TypeRecord> entry : recordTypes.entrySet()) {
			Set<String> keysInContext = entry.getValue().getTypes().stream().map(Pair::first).collect(Collectors.toSet());
			Set<String> keysInCheck = types.stream().map(Pair::first).collect(Collectors.toSet());
			if(keysInCheck.equals(keysInContext)) {
				return entry.getKey();
			}
		}
		return null;
	}

}
