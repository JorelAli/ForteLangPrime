package dev.jorel.fortelangprime.parser.exceptions;

public class InvalidPragmaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -51996578733573959L;

	private final String name;
	
	public InvalidPragmaException(String name) {
		this.name = name;
	}
	
	@Override
	public String getMessage() {
		return "Invalid pragma name '" + name + "'";
	}

}
