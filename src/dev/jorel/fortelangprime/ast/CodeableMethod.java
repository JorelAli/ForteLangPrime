package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.types.TypingContext;

public interface CodeableMethod extends Opcodes {
	
	public int getLineNumber();
	
	public default void emitLineNumber(MethodVisitor methodVisitor) {
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(getLineNumber(), lineNumber);
	}

	void emit(EmitterContext prog, MethodVisitor methodVisitor, TypingContext context);

	int returnType(TypingContext context);
	
}
