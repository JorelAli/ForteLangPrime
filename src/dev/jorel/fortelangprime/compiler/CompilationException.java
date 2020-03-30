package dev.jorel.fortelangprime.compiler;

public class CompilationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public CompilationException(String string) {
		this.message = string;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}

}
