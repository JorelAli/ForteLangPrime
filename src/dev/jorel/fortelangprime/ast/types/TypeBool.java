package dev.jorel.fortelangprime.ast.types;

public class TypeBool implements Type {

	@Override
	public String toBytecodeString() {
		return "Z";
	}

	@Override
	public int loadInstruction() {
		return ILOAD;
	}

}
