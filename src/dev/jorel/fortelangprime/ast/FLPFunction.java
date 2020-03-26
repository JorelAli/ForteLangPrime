package dev.jorel.fortelangprime.ast;

import java.util.Map.Entry;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypingContext;

public class FLPFunction implements CodeableClass {

	final String name;
	final TypeFunction typeFunction;
	final Expr body;
	
	public FLPFunction(String name, TypeFunction typeFunction, Expr body) {
		this.name = name;
		this.typeFunction = typeFunction;
		this.body = body;
	}
	
	@Override
	public void emit(ClassWriter classWriter, TypingContext context) {
		MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, name, typeFunction.toBytecodeString(), null, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(body.getLineNumber(), lineNumber);
		
		body.emit(methodVisitor, context);
		methodVisitor.visitInsn(body.returnType());
		
		Label variableTypes = new Label();
		methodVisitor.visitLabel(variableTypes);
		int index = 0;
		for(Entry<String, Type> e : typeFunction.getParams().entrySet()) {
			if(e.getKey() == null) {
				continue;
			}
			methodVisitor.visitLocalVariable(e.getKey(), e.getValue().toBytecodeString(), null, lineNumber, variableTypes, index++);
		}
		
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}
	
}
