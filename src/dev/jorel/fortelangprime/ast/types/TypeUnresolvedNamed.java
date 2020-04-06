package dev.jorel.fortelangprime.ast.types;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class TypeUnresolvedNamed implements Type {
	
	private String name;
	
	public TypeUnresolvedNamed(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public InternalType getInternalType() {
		return InternalType.UNRESOLVED_NAMED;
	}

	@Override
	public String toBytecodeString(UniversalContext context) {
		throw new RuntimeException("Cannot return bytecode string of unresolved named type " + name);
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
		throw new RuntimeException("Cannot return generic bytecode string of unresolved named type " + name);
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
