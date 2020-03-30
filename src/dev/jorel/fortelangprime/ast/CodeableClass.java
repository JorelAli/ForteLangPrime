package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.compiler.UniversalContext;

public interface CodeableClass extends Opcodes {

	void emit(EmitterContext proj, ClassWriter classWriter, UniversalContext context);
	
}
