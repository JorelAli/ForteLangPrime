package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public interface CodeableClass extends Opcodes {

	public void emit(ClassWriter writer);
	
}
