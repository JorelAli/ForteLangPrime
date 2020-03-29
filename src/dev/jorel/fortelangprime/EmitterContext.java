package dev.jorel.fortelangprime;

public class EmitterContext {

	private String libName;
	
	public EmitterContext(String libName) {
		this.libName = libName;
	}
	
	public String getLibraryName() {
		return this.libName;
	}

}
