package dev.jorel.fortelangprime.ast.types;

import java.util.LinkedHashMap;

public class TypeFunction implements Type {
	
	private Type returnType;
	private LinkedHashMap<String, Type> params;
	
	public TypeFunction(Type returnType, LinkedHashMap<String, Type> params) {
		this.returnType = returnType;
		this.params = params;
	}
	
	@Override
	public String toBytecodeString() {
		StringBuilder result = new StringBuilder("(");
		params.values().stream().map(Type::toBytecodeString).forEach(result::append);
		result.append(")");
		result.append(returnType.toBytecodeString());
		return result.toString();
	}
	
	public Type getReturnType() {
		return returnType;
	}

	public LinkedHashMap<String, Type> getParams() {
		return params;
	}

	@Override
	public int loadInstruction() {
		// TODO Auto-generated method stub
		return -1;
	}
	

}
