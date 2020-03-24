package dev.jorel.fortelangprime.abstractsyntaxtree.types;

public class ListType implements Type {
	
	//The type of this list
	private Type type;
	
	public ListType(Type type) {
		this.type = type;
	}

}
