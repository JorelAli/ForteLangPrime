package dev.jorel.fortelangprime.compiler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.parser.util.Pair;

/**
 * Context about the code, populated by the parser and used by everything
 */
public class UniversalContext {

	private String libName;
	private boolean allExports = false;
	Map<String, TypeFunction> functions;
	Map<String, TypeRecord> recordTypes;
	
	public UniversalContext() {
		this.functions = new HashMap<>();
		this.recordTypes = new HashMap<>();
	}
	
	public void exportAll() {
		this.allExports = true;
	}
	
	public boolean doesExportAll() {
		return this.allExports;
	}
	
	public void setLibraryName(String libName) {
		this.libName = libName;
	}
	
	public String getLibraryName() {
		return this.libName;
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
	
	public Type getRecordType(String recordName) {
		return this.recordTypes.get(recordName);
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
