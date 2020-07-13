package dev.jorel.fortelangprime.ast;

import java.util.List;
import java.util.stream.Collectors;

import dev.jorel.fortelangprime.ast.operation.CustomOperation;

public class FLPLibrary {

	public final String name;
	public final List<String> exports;
	public final List<CustomOperation> customOperations; 
	public final List<FLPFunction> functions;
	public final List<RecordTypeDeclaration> typeDeclarations;
	
	public final List<CodeableClass> thingsToEmit;
	
	public FLPLibrary(String name, List<String> exports, List<CodeableClass> thingsToEmit) {
		this.name = name;
		this.exports = exports;
		this.thingsToEmit = thingsToEmit;
		
		this.customOperations = thingsToEmit.stream().filter(CustomOperation.class::isInstance).map(CustomOperation.class::cast).collect(Collectors.toList());
		this.functions = thingsToEmit.stream().filter(FLPFunction.class::isInstance).map(FLPFunction.class::cast).collect(Collectors.toList());
		this.typeDeclarations = thingsToEmit.stream().filter(RecordTypeDeclaration.class::isInstance).map(RecordTypeDeclaration.class::cast).collect(Collectors.toList());
	}
	
}
