package dev.jorel.fortelangprime.ast.types;

import org.objectweb.asm.Opcodes;

public interface Type extends Opcodes {

	// Type signature
	public String toBytecodeString();
	
	// Generic type signature
	public String toGenericBytecodeString();
	public boolean isGeneric();
	
	// Bytecode instruction generation
	public int loadInstruction();
	public int returnType();
	
}
