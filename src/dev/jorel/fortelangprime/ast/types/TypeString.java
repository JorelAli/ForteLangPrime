package dev.jorel.fortelangprime.ast.types;

public class TypeString implements Type {

	@Override
	public String toBytecodeString() {
		return "Ljava/lang/String;";
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.STRING;
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
