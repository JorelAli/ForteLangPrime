package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.ast.types.TypingContext;

public interface CodeableClass extends Opcodes {

	void emit(ClassWriter classWriter, TypingContext context);
	
}
