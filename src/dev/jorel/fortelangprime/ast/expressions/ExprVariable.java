package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprVariable implements Expr {
	
	private int lineNumber;
	private String name;
	private String parentFunctionName;
	
	public ExprVariable(int lineNumber, String name, String parentFunctionName) {
		this.lineNumber = lineNumber;
		this.name = name;
		this.parentFunctionName = parentFunctionName;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		return context.getFunction(parentFunctionName).getParams().get(name);
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expr substitute(String name, Expr val) {
		return this;
	}

	@Override
	public Expr deepCopy() {
		return new ExprVariable(lineNumber, name, parentFunctionName);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.VARIABLE;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, TypingContext context) {
		int index = 0;
		for(String key : context.getFunction(parentFunctionName).getParams().keySet()) {
			if(key.equals(name)) {
				break;
			} else {
				index++;
			}
		}
		
		methodVisitor.visitVarInsn(context.getFunction(parentFunctionName).getParams().get(name).loadInstruction(), index);
//		Label label1 = new Label();
//		methodVisitor.visitLabel(label1);
//		methodVisitor.visitLocalVariable("a", "I", null, label0, label1, 0);
//		if(value) {
//			methodVisitor.visitInsn(ICONST_1);
//		} else {
//			methodVisitor.visitInsn(ICONST_0);
//		}
	}

	@Override
	public int returnType() {
		return ARETURN;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
