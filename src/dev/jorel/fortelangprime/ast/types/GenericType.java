package dev.jorel.fortelangprime.ast.types;

import org.objectweb.asm.Opcodes;

public interface GenericType extends Opcodes {

	public String toGenericBytecodeString();
	
}
