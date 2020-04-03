package dev.jorel.fortelangprime.parser.exceptions;

public class TypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeException(int lineNumber, String message) {
		super("Type error on line " + lineNumber + ": " + message);
	}

}
