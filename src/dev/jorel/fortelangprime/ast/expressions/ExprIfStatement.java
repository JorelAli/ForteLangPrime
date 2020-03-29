package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprIfStatement implements Expr {
	
	private int lineNumber;
	private Expr condition;
	private Expr ifTrue;
	private Expr ifFalse;
	
	public ExprIfStatement(int lineNumber, Expr condition, Expr ifTrue, Expr ifFalse) {
		this.lineNumber = lineNumber;
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		//TODO: Type checking occurs here - need to check if condition is 
		//'well typed' (integer) and if the ifTrue and ifFalse are of
		//the same type
		return ifTrue.getType(context);
	}

	@Override
	public boolean isReducable() {
		return true;
	}

	@Override
	public Expr substitute(String name, Expr val) {
		return this;
	}

	@Override
	public Expr deepCopy() {
		return new ExprIfStatement(lineNumber, condition, ifTrue, ifFalse);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.IF_STATEMENT;
	}

	@Override
	public void emit(EmitterContext prog, MethodVisitor methodVisitor, TypingContext context) {
	
		switch(condition.getInternalType()) {
			case BOOL_LITERAL: {
				ExprBoolLit bool = (ExprBoolLit) condition;
				if(bool.getValue()) {
					ifTrue.emitLineNumber(methodVisitor);
					ifTrue.emit(prog, methodVisitor, context);
				} else {
					ifFalse.emitLineNumber(methodVisitor);
					ifFalse.emit(prog, methodVisitor, context);
				}
				return;
			}
				
			case VARIABLE: {
				ExprVariable variable = (ExprVariable) condition;
				methodVisitor.visitVarInsn(ILOAD, variable.getIndex(context));
				
				Label ifFalseLabel = new Label();
				methodVisitor.visitJumpInsn(IFEQ, ifFalseLabel);
				
				ifTrue.emit(prog, methodVisitor, context);
				
				Label ifTrueLabel = new Label();
				methodVisitor.visitJumpInsn(GOTO, ifTrueLabel);
				
				methodVisitor.visitLabel(ifFalseLabel);
				ifFalse.emit(prog, methodVisitor, context);
				
				methodVisitor.visitLabel(ifTrueLabel);				
				return;
			}
			default:
				System.out.println("Can't perform condition on a " + condition.getInternalType());
				//Panic ??
				return;
		}

	}

	@Override
	public int returnType(TypingContext context) {
		return ifTrue.returnType(context);
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
