package dev.jorel.fortelangprime.compiler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import dev.jorel.fortelangprime.ast.operation.CustomOperation;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.parser.ParseException;
import dev.jorel.fortelangprime.util.Pair;

/**
 * Context about the code, populated by the parser and used by everything
 */
public class UniversalContext {

	private String libName;
	private int javaVersion;
	private File outputDir;
	private boolean allExports = false;
	List<CustomOperation> customOperations;
	Map<String, TypeFunction> functions;
	Map<String, TypeRecord> recordTypes;
	
	public UniversalContext() {
		customOperations = new ArrayList<>();
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
	
	public void addCustomOperation(CustomOperation op) throws ParseException {
		for(CustomOperation o : customOperations) {
			if(o.getOperatorToken().equals(op.getOperatorToken())) {
				throw new ParseException("Custom operator " + o.getOperatorToken() + " has already been declared ");
			}
		}
		customOperations.add(op);
		
		List<Pair<String, Type>> paramTypes = new ArrayList<>();
		paramTypes.add(op.getLeftType());
		paramTypes.add(op.getRightType());
		functions.put(op.getInternalName(), new TypeFunction(op.getReturnType(), paramTypes));
	}
	
	public CustomOperation searchCustomOperation(String opToken) {
		for(CustomOperation o : customOperations) {
			if(o.getOperatorToken().equals(opToken)) {
				return o;
			}
		}
		return null; //TODO: Error?
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
	
	public TypeRecord getRecordType(String recordName) {
		TypeRecord result = this.recordTypes.get(recordName);
		if(result == null) {
			throw new RuntimeException("Can't find " + recordName);
		} else {
			return result;
		}
	}
	
	public Type getRecordTypeMatching(List<Pair<String, Type>> types) {
		main: for(Entry<String, TypeRecord> entry : recordTypes.entrySet()) {
			Set<String> keysInContext = entry.getValue().getTypes().stream().map(Pair::first).collect(Collectors.toSet());
			Set<String> keysInCheck = types.stream().map(Pair::first).collect(Collectors.toSet());
			
			List<Type> typesInContext = entry.getValue().getTypes().stream().map(Pair::second).collect(Collectors.toList());
			List<Type> typesInCheck = types.stream().map(Pair::second).collect(Collectors.toList());
			
			if(typesInContext.size() == typesInCheck.size())  {
				for (int i = 0; i < typesInContext.size(); i++) {
					Type t = typesInContext.get(i);
					Type t2 = typesInCheck.get(i);
					if(t.getInternalType() != t2.getInternalType()) {
						continue main;
					}
				}
			}
			
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

	public void setJavaVersion(int javaVersion) {
		this.javaVersion = javaVersion;
	}
	
	public int getJavaVersion() {
		return this.javaVersion;
	}
	
	public File getOutputDir() {
		return this.outputDir;
	}
	
	public void setOutputDir(File outputDir) {
		this.outputDir = outputDir;
	}

}
