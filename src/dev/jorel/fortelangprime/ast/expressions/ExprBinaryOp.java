package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.operation.CustomOperation;
import dev.jorel.fortelangprime.ast.operation.Operation;
import dev.jorel.fortelangprime.ast.operation.StandardOperation;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class ExprBinaryOp implements Expr {
	
	private int lineNumber;
	private Expr left;
	private Expr right;
	private Operation op;
	private boolean hasBrackets;
	
	public ExprBinaryOp(int lineNumber, Expr left, Expr right, Operation op, boolean hasBrackets) {
		this.lineNumber = lineNumber;
		this.left = left;
		this.right = right;
		this.op = op;
		this.hasBrackets = hasBrackets;
	}

	@Override
	public Type getType(UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
				return new TypeBool();
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
//			operation.
		}
		
		return null;
	}
	
	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
				if(left.typeCheck(context).getInternalType() != right.typeCheck(context).getInternalType()) {
					throw new TypeException("Left is " + left.getType(context).getInternalType() + " but right is " + right.getType(context).getInternalType());
				}
				return new TypeBool();
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
//			operation.
			return null;
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
		return new ExprBinaryOp(lineNumber, left, right, op, hasBrackets);
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
				left.emit(methodVisitor, context);
				right.emit(methodVisitor, context);
				Label end = new Label();
				Label ifEqual = new Label();
				
				if(left.getType(context).comparingInstruction() == IF_ACMPEQ) {
					methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
					methodVisitor.visitJumpInsn(IFNE, ifEqual);
				} else {
					methodVisitor.visitJumpInsn(left.getType(context).comparingInstruction(), ifEqual);
				}
				methodVisitor.visitInsn(ICONST_0);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_1);
				
				methodVisitor.visitLabel(end);
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
//			operation.
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
				return IRETURN;
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
//			operation.
		}
		return -1;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
