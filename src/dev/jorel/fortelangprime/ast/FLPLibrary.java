package dev.jorel.fortelangprime.ast;

import java.util.ArrayList;
import java.util.List;

import dev.jorel.fortelangprime.ast.operation.CustomOperation;

public class FLPLibrary {

	public final String name;
	public final List<String> exports;
	public final List<CustomOperation> customOperations; 
	public final List<FLPFunction> functions;
	public final List<RecordTypeDeclaration> typeDeclarations;
	
	public final List<CodeableClass> thingsToEmit;
	
	public FLPLibrary(String name, List<String> exports, List<CustomOperation> customOperations, List<FLPFunction> functions, List<RecordTypeDeclaration> typeDeclarations) {
		this.name = name;
		this.exports = exports;
		this.customOperations = customOperations;
		this.functions = functions;
		this.typeDeclarations = typeDeclarations;
		
		thingsToEmit = new ArrayList<>();
		thingsToEmit.addAll(customOperations);
		thingsToEmit.addAll(functions);
		thingsToEmit.addAll(typeDeclarations);
	}
	
}
