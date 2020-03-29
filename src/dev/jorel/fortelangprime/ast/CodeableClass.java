package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.types.TypingContext;

public interface CodeableClass extends Opcodes {

	void emit(EmitterContext proj, ClassWriter classWriter, TypingContext context);
	
}
