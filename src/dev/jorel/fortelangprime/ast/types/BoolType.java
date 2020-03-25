package dev.jorel.fortelangprime.ast.types;

public class BoolType implements Type {

	@Override
	public String toBytecodeString() {
		return "Z";
	}

}
