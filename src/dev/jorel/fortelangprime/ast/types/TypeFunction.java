package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.parser.util.Pair;

public class TypeFunction implements Type, GenericType {
	
	private Type returnType;
	private List<Pair<String, Type>> params;
	
	public TypeFunction(Type returnType, List<Pair<String, Type>> params) {
		this.returnType = returnType;
		this.params = params;
	}
	
	@Override
	public String toBytecodeString() {
		StringBuilder result = new StringBuilder("(");
		params.stream().map(Pair::second).map(Type::toBytecodeString).forEach(result::append);
		result.append(")");
		result.append(returnType.toBytecodeString());
		return result.toString();
	}
	
	public String toGenericBytecodeString() {
		StringBuilder result = new StringBuilder("(");
		for(Pair<String, Type> param : params) {
			if(param.second() instanceof TypeNamedGeneric) {
				result.append(((TypeNamedGeneric) param.second()).toGenericBytecodeString());
			} else {
				result.append(param.second().toBytecodeString());
			}
		}
		result.append(")");
		if(returnType instanceof TypeNamedGeneric) {
			result.append(((TypeNamedGeneric) returnType).toGenericBytecodeString());
		} else {
			result.append(returnType.toBytecodeString());
		}
		return result.toString();
	}
	
	public Type getReturnType() {
		return returnType;
	}

	public List<Pair<String, Type>> getParams() {
		return params;
	}

	@Override
	public int loadInstruction() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public int returnType() {
		return returnType.returnType();
	}
	

}
