package dev.jorel.fortelangprime.ast;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.util.Pair;

public class FLPFunction implements CodeableClass {

	final private String name;
	final private TypeFunction typeFunction;
	final private Expr body;
	private boolean exported;
	
	public FLPFunction(String name, TypeFunction typeFunction, Expr body) {
		this.name = name;
		this.typeFunction = typeFunction;
		this.body = body;
		this.exported = false;
	}
	
	public void setPublic() {
		this.exported = true;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void emit(ClassWriter classWriter, TypingContext context) {
		MethodVisitor methodVisitor = classWriter.visitMethod((exported ? ACC_PUBLIC : ACC_PRIVATE) | ACC_STATIC, name, typeFunction.toBytecodeString(), null, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(body.getLineNumber(), lineNumber);
		
		body.emit(methodVisitor, context);
		methodVisitor.visitInsn(body.returnType(context));
		
		Label variableTypes = new Label();
		methodVisitor.visitLabel(variableTypes);
		int index = 0;
		for(Pair<String, Type> e : typeFunction.getParams()) {
			if(e.first() == null) {
				continue;
			}
			methodVisitor.visitLocalVariable(e.first(), e.second().toBytecodeString(), null, lineNumber, variableTypes, index++);
		}
		
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}
	
}
