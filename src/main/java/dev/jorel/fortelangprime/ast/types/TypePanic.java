package dev.jorel.fortelangprime.ast.types;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class TypePanic implements Type {

	@Override
	public String toBytecodeString(UniversalContext context) {
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
	public String toGenericBytecodeString(UniversalContext context) {
		return null;
	}

	@Override
	public boolean isGeneric() {
		return false;
	}
	
	@Override
	public int comparingInstruction() {
		return -1;
	}
	
	@Override
	public int negativeComparingInstruction() {
		return -1;
	}
}
