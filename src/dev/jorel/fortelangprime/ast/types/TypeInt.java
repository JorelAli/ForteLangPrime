package dev.jorel.fortelangprime.ast.types;

public class TypeInt implements Type {

	@Override
	public String toBytecodeString() {
		return "I";
	}
	
	@Override
	public int loadInstruction() {
		return ILOAD;
	}

}
