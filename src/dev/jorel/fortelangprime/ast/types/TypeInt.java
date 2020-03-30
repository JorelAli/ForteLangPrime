package dev.jorel.fortelangprime.ast.types;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class TypeInt implements Type {

	@Override
	public String toBytecodeString(UniversalContext context) {
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
	public String toGenericBytecodeString(UniversalContext context) {
		return null;
	}

	@Override
	public boolean isGeneric() {
		return false;
	}
	
	@Override
	public int comparingInstruction() {
		return IF_ICMPEQ;
	}
}
