package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.InternalType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * An if statement. if a then b else c
 */
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
	public String toString() {
		return "if " + condition + " then " + ifTrue + " else " + ifFalse;
	}

	@Override
	public Type getType(UniversalContext context) {
		return ifTrue.getType(context);
	}
	
	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		if(condition.typeCheck(context).getInternalType() != InternalType.BOOLEAN) {
			if(condition.typeCheck(context).getInternalType() == InternalType.FUNCTION) {
				TypeFunction tf = (TypeFunction) condition.typeCheck(context);
				if(tf.getReturnType().getInternalType() != InternalType.BOOLEAN) {
					throw new TypeException("Condition on " + condition.getLineNumber() + " is not a boolean");
				}
			} else {
				throw new TypeException("Condition on " + condition.getLineNumber() + " is not a boolean");
			}
		}
		if(ifTrue.getInternalType() != ifFalse.getInternalType()) {
			StringBuilder builder = new StringBuilder("Return types on ");
			builder.append(ifTrue.getLineNumber());
			builder.append(":");
			builder.append(ifTrue.getInternalType());
			builder.append(" and ");
			builder.append(ifFalse.getLineNumber());
			builder.append(":");
			builder.append(ifFalse.getInternalType());
			builder.append(" are different types");
			throw new TypeException(builder.toString());
		}
		return getType(context);
	}

	@Override
	public ExpressionType getInternalType() {
		return ifTrue.getInternalType();
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
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
	
		switch(condition.getInternalType()) {
			case BOOL_LITERAL: {
				ExprBoolLit bool = (ExprBoolLit) condition;
				if(bool.getValue()) {
					FLPCompiler.log("Emitting conditional boolean always true");
					ifTrue.emitLineNumber(methodVisitor);
					ifTrue.emit(methodVisitor, context);
				} else {
					FLPCompiler.log("Emitting conditional boolean always false");
					ifFalse.emitLineNumber(methodVisitor);
					ifFalse.emit(methodVisitor, context);
				}
				return;
			}
				
			case VARIABLE: {
				FLPCompiler.log("Emitting conditional value");
				ExprVariable variable = (ExprVariable) condition;
				variable.emit(methodVisitor, context);
				
				Label ifFalseLabel = new Label();
				methodVisitor.visitJumpInsn(IFEQ, ifFalseLabel);
				
				FLPCompiler.log("Emitting case when conditional is true");
				ifTrue.emit(methodVisitor, context);
				
				Label ifTrueLabel = new Label();
				methodVisitor.visitJumpInsn(GOTO, ifTrueLabel);
				
				methodVisitor.visitLabel(ifFalseLabel);
				FLPCompiler.log("Emitting case when conditional is false");
				ifFalse.emit(methodVisitor, context);
				
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
	public int returnType(UniversalContext context) {
		return ifTrue.returnType(context);
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
