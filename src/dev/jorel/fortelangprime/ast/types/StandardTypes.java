package dev.jorel.fortelangprime.ast.types;

public class StandardTypes {
	
	public static Type StringType() {
		return new TypeString();
	}
	
	public static Type BoolType() {
		return new TypeBool	();
	}

}
