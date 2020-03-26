package dev.jorel.fortelangprime.ast.types;

public class TypePanic implements Type {

	@Override
	public String toBytecodeString() {
		return "?";
	}
	
	@Override
	public int loadInstruction() {
		return -1;
	}

	@Override
	public int returnType() {
		return ATHROW;
	}
}
