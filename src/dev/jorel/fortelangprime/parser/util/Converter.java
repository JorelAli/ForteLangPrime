package dev.jorel.fortelangprime.parser.util;

import java.util.List;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypeFunctionBuilder;

public class Converter {

	public static TypeFunction functionTypesToTypeFunction(List<Pair<String, Type>> functionTypes) {
		TypeFunctionBuilder builder = new TypeFunctionBuilder();
		for(int i = 0; i < functionTypes.size() - 1; i++) {
			Pair<String, Type> pair = functionTypes.get(i);
			builder.withParam(pair.first, pair.second);
		}
		builder.returning(functionTypes.get(functionTypes.size() - 1).second);
		return builder.build();
	}
	
}
