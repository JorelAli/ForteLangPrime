package dev.jorel.fortelangprime.ast.types;

public class TypePanic implements Type {

	@Override
	public String toBytecodeString() {
		return "?";
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.PANIC;
	}
	
	@Override
	public int loadInstruction() {
		return -1;
	}

	@Override
	public int returnType() {
		return ATHROW;
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
