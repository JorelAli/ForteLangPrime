package dev.jorel.fortelangprime.ast.types;

public class StringType implements Type {

	@Override
	public String toBytecodeString() {
		return "Ljava/lang/String";
	}

}
