package dev.jorel.fortelangprime.ast.types;

public class TypeInt implements Type {

	@Override
	public String toBytecodeString() {
		return "I";
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.INTEGER;
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
