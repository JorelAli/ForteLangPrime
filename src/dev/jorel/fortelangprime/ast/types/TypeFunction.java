package dev.jorel.fortelangprime.ast.types;

import java.util.List;
import java.util.ListIterator;

import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.util.Pair;
import dev.jorel.fortelangprime.util.StreamUtils;

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
		getParams(context).stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).forEach(result::append);
		result.append(")");		
		result.append(getReturnType(context).toBytecodeString(context));
		return result.toString();
	}
	
	public String toGenericBytecodeString(UniversalContext context) {
		StringBuilder result = new StringBuilder("(");
		getParams(context).stream().map(Pair::second)
			.map(StreamUtils.conditioning(Type::isGeneric, t -> t.toGenericBytecodeString(context), StreamUtils.with(Type::toBytecodeString, context)))
			.forEach(result::append);
		result.append(")");
		
		if(getReturnType(context).isGeneric()) {
			result.append(getReturnType(context).toGenericBytecodeString(context));
		} else {
			result.append(getReturnType(context).toBytecodeString(context));
		}
		return result.toString();
	}
	
	public Type getReturnType(UniversalContext context) {
		if(returnType.getInternalType() == InternalType.UNRESOLVED_NAMED) {
			this.returnType = context.getRecordType(((TypeUnresolvedNamed) returnType).getName());
		}
		return this.returnType;
	}
	
	public List<Pair<String, Type>> getParams(UniversalContext context) {
		ListIterator<Pair<String, Type>> li = this.params.listIterator();
		while(li.hasNext()) {
			Pair<String, Type> next = li.next();
			if(next.second().getInternalType() == InternalType.UNRESOLVED_NAMED) {
				TypeUnresolvedNamed unresolvedNamedType = (TypeUnresolvedNamed) next.second();
				Type result = context.getRecordType(unresolvedNamedType.getName());
				if(result != null) {
					li.set(Pair.of(next.first(), result));
				}
			}
		}
		return this.params;
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
