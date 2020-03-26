package dev.jorel.fortelangprime.ast.types;

import java.util.ArrayList;
import java.util.List;

import dev.jorel.fortelangprime.parser.util.Pair;

public class TypeFunctionBuilder {

	Type returnType;
	List<Pair<String, Type>> params;
	
	public TypeFunctionBuilder() {
		params = new ArrayList<>();
	}
	
	public TypeFunctionBuilder withParam(String name, Type paramType) {
		params.add(Pair.of(name, paramType));
		return this;
	}
	
	public TypeFunctionBuilder returning(Type returnType) {
		this.returnType = returnType;
		return this;
	}
	
	public TypeFunction build() {
		return new TypeFunction(returnType, params);
	}
}