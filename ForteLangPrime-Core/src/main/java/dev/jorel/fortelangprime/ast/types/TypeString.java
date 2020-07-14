package dev.jorel.fortelangprime.ast.types;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class TypeString implements Type {

	@Override
	public String toBytecodeString(UniversalContext context) {
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
