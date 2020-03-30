package dev.jorel.fortelangprime.ast.types;

public class TypeBool implements Type {

	@Override
	public String toBytecodeString() {
		return "Z";
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.BOOLEAN;
	}

	@Override
	public int loadInstruction() {
		return ILOAD;
	}

	@Override
	public int returnType() {
		return IRETURN;
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
