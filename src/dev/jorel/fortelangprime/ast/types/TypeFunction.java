package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.util.Pair;
import dev.jorel.fortelangprime.parser.util.StreamUtils;

public class TypeFunction implements Type {
	
	private Type returnType;
	private List<Pair<String, Type>> params;
	
	public TypeFunction(Type returnType, List<Pair<String, Type>> params) {
		this.returnType = returnType;
		this.params = params;
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.FUNCTION;
	}
	
	@Override
	public String toBytecodeString(UniversalContext context) {
		StringBuilder result = new StringBuilder("(");
		params.stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).forEach(result::append);
		result.append(")");
		result.append(returnType.toBytecodeString(context));
		return result.toString();
	}
	
	public String toGenericBytecodeString(UniversalContext context) {
		StringBuilder result = new StringBuilder("(");
		params.stream().map(Pair::second)
			.map(StreamUtils.conditioning(Type::isGeneric, t -> t.toGenericBytecodeString(context), StreamUtils.with(Type::toBytecodeString, context)))
			.forEach(result::append);
		result.append(")");
		
		if(returnType.isGeneric()) {
			result.append(returnType.toGenericBytecodeString(context));
		} else {
			result.append(returnType.toBytecodeString(context));
		}
		return result.toString();
	}
	
	public Type getReturnType() {
		return returnType;
	}

	public List<Pair<String, Type>> getParams() {
		return params;
	}

	@Override
	public int loadInstruction() {
		return -1;
	}
	
	@Override
	public int returnType() {
		return returnType.returnType();
	}

	@Override
	public boolean isGeneric() {
		return true;
	}
	
	@Override
	public int comparingInstruction() {
		return -1;
	}
	
	@Override
	public int negativeComparingInstruction() {
		return -1;
	}

}
