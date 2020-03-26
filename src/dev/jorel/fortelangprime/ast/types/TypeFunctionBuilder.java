package dev.jorel.fortelangprime.ast.types;

import java.util.LinkedHashMap;

public class TypeFunctionBuilder {

	Type returnType;
	LinkedHashMap<String, Type> params;
	
	public TypeFunctionBuilder() {
		params = new LinkedHashMap<>();
	}
	
	public TypeFunctionBuilder withParam(String name, Type paramType) {
		params.put(name, paramType);
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