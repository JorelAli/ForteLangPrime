package dev.jorel.fortelangprime.ast.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.expressions.ExprBinaryOp;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable.LeftBracket;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable.RightBracket;

public class ShuntingYard {
	
	public static List<ShuntingYardable> flatten(ExprBinaryOp op) {
		List<ShuntingYardable> tokens = new ArrayList<>();
		if(op.hasBrackets()) {
			tokens.add(new LeftBracket());
		}
		
		if(op.getLeft() instanceof ExprBinaryOp) {
			tokens.addAll(flatten((ExprBinaryOp) op.getLeft()));
		} else {
			tokens.add(op.getLeft());
		}
		
		tokens.add(op.getOperation());
		
		if(op.getRight() instanceof ExprBinaryOp) {
			tokens.addAll(flatten((ExprBinaryOp) op.getRight()));
		} else {
			tokens.add(op.getRight());
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
			if(element instanceof Expr) {
				outputQueue.add(element);
			}
			if(element instanceof Operation) {
				Operation operation = (Operation) element;
				while(!operatorStack.isEmpty() && !(operatorStack.peek() instanceof LeftBracket) &&
					((((Operation) operatorStack.peek()).getPrecedence() > operation.getPrecedence())
					|| (((Operation) operatorStack.peek()).getPrecedence() == operation.getPrecedence() && operation.getAssociativity() == Associativity.LEFT))
				) {
					outputQueue.add(operatorStack.pop());
				}
				operatorStack.add(element);
			}
			if(element instanceof LeftBracket) {
				operatorStack.add(element);
			}
			if(element instanceof RightBracket) {
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
