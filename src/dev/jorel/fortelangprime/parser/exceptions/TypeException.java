package dev.jorel.fortelangprime.parser.exceptions;

public class TypeException extends Exception {

	private String message;
	
	public TypeException(String string) {
		this.message = string;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
