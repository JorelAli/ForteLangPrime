package dev.jorel.fortelangprime.ast;

import java.util.List;

public class FLPLibrary {

	public String name;
	public List<String> exports;
	public List<FLPFunction> functions;
	
	public FLPLibrary(String name, List<String> exports, List<FLPFunction> functions) {
		this.name = name;
		this.exports = exports;
		this.functions = functions;
	}
	
}
