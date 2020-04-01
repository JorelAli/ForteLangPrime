package dev.jorel.fortelangprime.ast.operation;

public class ShuntingYard {

//	while there are tokens to be read do:
//	    read a token.
//	    if the token is a number, then:
//	        push it to the output queue.
//	    if the token is a function then:
//	        push it onto the operator stack 
//	    if the token is an operator, then:
//	        while ((there is a function at the top of the operator stack)
//	               or (there is an operator at the top of the operator stack with greater precedence)
//	               or (the operator at the top of the operator stack has equal precedence and the token is left associative))
//	              and (the operator at the top of the operator stack is not a left parenthesis):
//	            pop operators from the operator stack onto the output queue.
//	        push it onto the operator stack.
//	    if the token is a left paren (i.e. "("), then:
//	        push it onto the operator stack.
//	    if the token is a right paren (i.e. ")"), then:
//	        while the operator at the top of the operator stack is not a left paren:
//	            pop the operator from the operator stack onto the output queue.
//	        /* If the stack runs out without finding a left paren, then there are mismatched parentheses. */
//	        if there is a left paren at the top of the operator stack, then:
//	            pop the operator from the operator stack and discard it
//	/* After while loop, if operator stack not null, pop everything to output queue */
//	if there are no more tokens to read then:
//	    while there are still operator tokens on the stack:
//	        /* If the operator token on the top of the stack is a paren, then there are mismatched parentheses. */
//	        pop the operator from the operator stack onto the output queue.
//	exit.
	
}
