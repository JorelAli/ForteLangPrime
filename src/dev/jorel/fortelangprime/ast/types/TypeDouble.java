package dev.jorel.fortelangprime.ast.types;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class TypeDouble implements Type {

	@Override
	public String toBytecodeString(UniversalContext context) {
		return "D";
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.DOUBLE;
	}
	
	@Override
	public int loadInstruction() {
		return DLOAD;
	}

	@Override
	public int returnType() {
		return DRETURN;
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
		System.out.println("CompDoub <--");
		return DCMPL;
	}
	
	@Override
	public int negativeComparingInstruction() {
		System.out.println("NegCompDoub");
		return -1;
//		return IF_ICMPNE;
	}
}
