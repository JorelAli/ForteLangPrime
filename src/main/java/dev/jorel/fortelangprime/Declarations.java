package dev.jorel.fortelangprime;

import java.util.ArrayList;
import java.util.List;

import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.RecordTypeDeclaration;

public class Declarations {

	List<RecordTypeDeclaration> typeDeclarations;
	List<FLPFunction> functions;
	
	public Declarations() {
		this.typeDeclarations = new ArrayList<>();
		this.functions = new ArrayList<>();
	}
	
	public void add(RecordTypeDeclaration d) {
		this.typeDeclarations.add(d);
	}
	
	public void add(FLPFunction f) {
		this.functions.add(f);
	}
	
}
