package dev.jorel.fortelangprime.ast.types;

public class TypePanic implements Type {

	@Override
	public String toBytecodeString() {
		return "?";
	}

}
