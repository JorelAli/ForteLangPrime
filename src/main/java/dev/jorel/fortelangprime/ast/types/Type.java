package dev.jorel.fortelangprime.ast.types;

import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public interface Type extends Opcodes {

	// Type signature
	public String toBytecodeString(UniversalContext context);
	
	// Generic type signature
	public String toGenericBytecodeString(UniversalContext context);
	public boolean isGeneric();
	
	// Bytecode instruction generation
	public int loadInstruction();
	public int comparingInstruction();
	public int negativeComparingInstruction();
	public int returnType();
	
	// Other stuff
	public InternalType getInternalType();

	
}
