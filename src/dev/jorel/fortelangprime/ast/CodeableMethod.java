package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public interface CodeableMethod extends Opcodes {

	public void emit(MethodVisitor methodVisitor);
	
	public int returnType();
	
}
