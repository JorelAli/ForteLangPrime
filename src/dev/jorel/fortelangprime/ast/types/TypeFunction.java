package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.parser.util.Pair;

public class TypeFunction implements Type {
	
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
	

}
