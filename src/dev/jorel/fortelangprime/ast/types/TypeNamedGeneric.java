package dev.jorel.fortelangprime.ast.types;

import dev.jorel.fortelangprime.compiler.UniversalContext;

// This should ONLY be used for generics, during type checking these should
// no longer be used for things such as return types for type functions
//@Deprecated
public class TypeNamedGeneric implements Type {
	
	private String name;
	
	public TypeNamedGeneric(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	@Override
	public InternalType getInternalType() {
		return InternalType.NAMED_GENERIC;
	}

	@Override
	public String toBytecodeString(UniversalContext context) {
		return "L" + context.getLibraryName() + "$" + name + ";";
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
		return "T" + name + ";";
	}

	@Override
	public boolean isGeneric() {
		return true;
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
