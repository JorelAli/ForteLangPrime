package dev.jorel.fortelangprime.ast.types;

public class IntType implements Type {

	@Override
	public String toBytecodeString() {
		return "I";
	}

}
