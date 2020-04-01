package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.util.Pair;

public class TypeRecord implements Type {
	
	private String name;
	private List<Pair<String, Type>> types;
	
	public TypeRecord(String name, List<Pair<String, Type>> types) {
		this.name = name;
		this.types = types;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.RECORD;
	}
	
	public List<Pair<String, Type>> getTypes() {
		return this.types;
	}

	@Override
	public String toBytecodeString(UniversalContext context) {
		return "$" + name + ";";
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
	public String toGenericBytecodeString(UniversalContext context) {
		return null;
	}

	@Override
	public boolean isGeneric() {
		return false;
	}
	
	@Override
	public int comparingInstruction() {
		return IF_ACMPEQ;
	}
	
	@Override
	public int negativeComparingInstruction() {
		return IF_ACMPNE;
	}

}
