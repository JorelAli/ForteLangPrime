package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprBinaryOp implements Expr {
	
	private int lineNumber;
	private Expr left;
	private Expr right;
	private Operation op;
	
	public ExprBinaryOp(int lineNumber, Expr left, Expr right, Operation op) {
		this.lineNumber = lineNumber;
		this.left = left;
		this.right = right;
		this.op = op;
	}

	@Override
	public Type getType(UniversalContext context) {
		switch(op) {
			case EQUALS_EQUALS:
				return new TypeBool();
		}
		return null;
	}
	
	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		switch(op) {
			case EQUALS_EQUALS:
				if(left.typeCheck(context).getInternalType() != right.typeCheck(context).getInternalType()) {
					throw new TypeException("Left is " + left.getType(context).getInternalType() + " but right is " + right.getType(context).getInternalType());
				}
				return new TypeBool();
		}
		return null;
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.BINARY_OPERATION;
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
		return new ExprBinaryOp(lineNumber, left, right, op);
	}

	@Override
	public void emit(EmitterContext prog, MethodVisitor methodVisitor, UniversalContext context) {
		switch(op) {
			case EQUALS_EQUALS:
				left.emit(prog, methodVisitor, context);
				right.emit(prog, methodVisitor, context);
				Label end = new Label();
				Label ifEqual = new Label();
				methodVisitor.visitJumpInsn(IF_ICMPEQ, ifEqual);
				methodVisitor.visitInsn(ICONST_0);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_1);
				
				methodVisitor.visitLabel(end);
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		switch(op) {
			case EQUALS_EQUALS:
				return IRETURN;
		}
		return 0;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
