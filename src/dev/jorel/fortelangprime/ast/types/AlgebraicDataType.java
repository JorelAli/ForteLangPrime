package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class AlgebraicDataType implements Type{

	private String name;
	private List<String> values;
	
	
	public AlgebraicDataType(String name, List<String> values) {
		this.name = name;
		this.values = values;
	}

	@Override
	public String toBytecodeString(UniversalContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toGenericBytecodeString(UniversalContext context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isGeneric() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int loadInstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int comparingInstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int negativeComparingInstruction() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int returnType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InternalType getInternalType() {
		// TODO Auto-generated method stub
		return null;
	}

}
