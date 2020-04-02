package dev.jorel.fortelangprime.ast.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.expressions.ExprBinaryOp;
import dev.jorel.fortelangprime.ast.expressions.ExprInternalCast;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable.LeftBracket;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable.RightBracket;

public class ShuntingYard implements Opcodes {
	
	private static void flattenRight(ExprBinaryOp op, List<ShuntingYardable> tokens) {
		if(op.getOperation() != StandardOperation.ACCESSRECORD) {
			if(op.getRight() instanceof ExprBinaryOp) {
				tokens.addAll(flatten((ExprBinaryOp) op.getRight()));
			} else {
				tokens.add(op.getRight());
			}
		}
	}
	
	private static void flattenLeft(ExprBinaryOp op, List<ShuntingYardable> tokens) {
		if(op.getLeft() instanceof ExprBinaryOp) {
			tokens.addAll(flatten((ExprBinaryOp) op.getLeft()));
		} else {
			tokens.add(op.getLeft());
		}
	}
	
	public static List<ShuntingYardable> flatten(ExprBinaryOp op) {
		List<ShuntingYardable> tokens = new ArrayList<>();
		if(op.hasBrackets()) {
			tokens.add(new LeftBracket());
		}
		
		// Left expression 
		if(op.getOperation().getAssociativity() == Associativity.RIGHT) {
			flattenRight(op, tokens);
		} else {
			flattenLeft(op, tokens);
		}
		
		// Left expression casting checks
		if(op.getOperation() == StandardOperation.POW) {
			tokens.add(new ExprInternalCast(I2D));
		}
		
		// Operation
		tokens.add(op);
		
		// Right expression
		if(op.getOperation().getAssociativity() == Associativity.RIGHT) {
			flattenLeft(op, tokens);
		} else {
			flattenRight(op, tokens);
		}
		
		if(op.hasBrackets()) {
			tokens.add(new RightBracket());
		}
		
		return tokens;
	}
	
	public static List<ShuntingYardable> doShuntingYard(List<ShuntingYardable> elements) {
		
		List<ShuntingYardable> outputQueue = new ArrayList<>();
		Stack<ShuntingYardable> operatorStack = new Stack<>();
		
		while(!elements.isEmpty()) {
			ShuntingYardable element = elements.get(0);
			elements.remove(0);
			if(element instanceof ExprBinaryOp) {
				Operation operation = (Operation) ((ExprBinaryOp) element).getOperation();
				while(!operatorStack.isEmpty() && !(operatorStack.peek() instanceof LeftBracket) &&
					((((ExprBinaryOp) operatorStack.peek()).getOperation().getPrecedence() > operation.getPrecedence())
					|| (((ExprBinaryOp) operatorStack.peek()).getOperation().getPrecedence() == operation.getPrecedence() && operation.getAssociativity() == Associativity.LEFT))
				) {
					outputQueue.add(operatorStack.pop());
				}
				operatorStack.add(element);
			} else if(element instanceof Expr) {
				outputQueue.add(element);
			} else if(element instanceof LeftBracket) {
				operatorStack.add(element);
			} else if(element instanceof RightBracket) {
				while(!(operatorStack.peek() instanceof LeftBracket)) {
					outputQueue.add(operatorStack.pop());
				}
				if(operatorStack.peek() instanceof LeftBracket) {
					operatorStack.pop();
				}
			}
		}
		while(!operatorStack.isEmpty()) {
			outputQueue.add(operatorStack.pop());
		}
		
		return outputQueue;
	}
	
}
