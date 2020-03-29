package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.parser.util.Pair;

public class TypeRecord implements Type {
	
	private List<Pair<String, Type>> types;
	
	public TypeRecord(List<Pair<String, Type>> types) {
		this.types = types;
	}
	
	public List<Pair<String, Type>> getTypes() {
		return this.types;
	}

	@Override
	public String toBytecodeString() {
		return "L";
	}

	@Override
	public int loadInstruction() {
		return ALOAD;
	}

	@Override
	public int returnType() {
		return ARETURN;
	}

	@Override
	public String toGenericBytecodeString() {
		return null;
	}

	@Override
	public boolean isGeneric() {
		return false;
	}

}
