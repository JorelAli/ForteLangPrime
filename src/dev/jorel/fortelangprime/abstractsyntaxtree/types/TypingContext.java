package dev.jorel.fortelangprime.abstractsyntaxtree.types;

import java.util.HashMap;

import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class TypingContext extends HashMap<String, Type> {
	
	public Type get(String key) throws TypeException {
		Type result = super.get(key);
		if(result == null) {
			throw new TypeException();
		} else {
			return result;
		}
	}

}
