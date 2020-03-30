package dev.jorel.fortelangprime.ast.types;

public class TypeNamedGeneric implements Type {
	
	private String name;
	
	public TypeNamedGeneric(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public InternalType getInternalType() {
		return InternalType.NAMED_GENERIC;
	}

	@Override
	public String toBytecodeString() {
		return "Ljava/lang/Object;";
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
		return "T" + name + ";";
	}

	@Override
	public boolean isGeneric() {
		return true;
	}

}
