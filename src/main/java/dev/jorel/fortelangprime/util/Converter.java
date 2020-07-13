package dev.jorel.fortelangprime.util;

import java.util.List;

import dev.jorel.fortelangprime.ast.types.InternalType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.ast.types.TypeFunctionBuilder;
import dev.jorel.fortelangprime.ast.types.TypeGeneric;
import dev.jorel.fortelangprime.ast.types.TypeUnresolvedNamed;

public class Converter {

	public static TypeFunction functionTypesToTypeFunction(List<Pair<String, Type>> functionTypes) {
		TypeFunctionBuilder builder = new TypeFunctionBuilder();
		for(int i = 0; i < functionTypes.size() - 1; i++) {
			Pair<String, Type> pair = functionTypes.get(i);
			builder.withParam(pair.first(), pair.second());
		}
		builder.returning(functionTypes.get(functionTypes.size() - 1).second());
		return builder.build();
	}
	
	public static TypeFunction functionTypesToTypeFunction(List<Pair<String, Type>> functionTypes, List<String> generics) {
		TypeFunctionBuilder builder = new TypeFunctionBuilder();
		for(int i = 0; i < functionTypes.size() - 1; i++) {
			Pair<String, Type> pair = functionTypes.get(i);
			Type type = pair.second();
			
			//Convert unresolved named into generics if applicable
			if(pair.second().getInternalType() == InternalType.UNRESOLVED_NAMED) {
				TypeUnresolvedNamed named = (TypeUnresolvedNamed) pair.second();
				if(generics.contains(named.getName())) {
					type = new TypeGeneric(named.getName());
				}
			}
			builder.withParam(pair.first(), type);
		}
		
		//Convert unresolved named into generics for return type
		Type last = functionTypes.get(functionTypes.size() - 1).second();
		if(last.getInternalType() == InternalType.UNRESOLVED_NAMED) {
			TypeUnresolvedNamed named = (TypeUnresolvedNamed) last;
			if(generics.contains(named.getName())) {
				last = new TypeGeneric(named.getName());
			}
		}
		builder.returning(last);
		return builder.build();
	}
	
}
