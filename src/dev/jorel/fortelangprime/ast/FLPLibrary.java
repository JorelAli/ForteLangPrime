package dev.jorel.fortelangprime.ast;

import java.util.List;

public class FLPLibrary {

	public String name;
	public List<String> exports;
	public List<FLPFunction> functions;
	public List<RecordTypeDeclaration> typeDeclarations;
	
	public FLPLibrary(String name, List<String> exports, List<FLPFunction> functions, List<RecordTypeDeclaration> typeDeclarations) {
		this.name = name;
		this.exports = exports;
		this.functions = functions;
		this.typeDeclarations = typeDeclarations;
	}
	
}
