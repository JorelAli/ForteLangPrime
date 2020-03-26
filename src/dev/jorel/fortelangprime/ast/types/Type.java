package dev.jorel.fortelangprime.ast.types;

import org.objectweb.asm.Opcodes;

public interface Type extends Opcodes {

	public String toBytecodeString();
	
	public int loadInstruction();
	
	public int returnType();
	
}
