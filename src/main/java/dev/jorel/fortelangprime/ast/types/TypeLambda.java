package dev.jorel.fortelangprime.ast.types;

import java.util.List;

import dev.jorel.fortelangprime.compiler.UniversalContext;

public class TypeLambda implements Type {
	
	private Type left;
	private List<Type> right;
	
	//methodVisitor.visitLocalVariable("i", "Ljava/util/function/Function;", "Ljava/util/function/Function<Ljava/lang/Integer;Ljava/lang/Integer;>;", label1, label21, 1);
	
	public TypeLambda(Type left, List<Type> right) { 
		this.left = left;
		this.right = right;
	}
	
	@Override
	public InternalType getInternalType() {
		return InternalType.LAMBDA;
	}
	
	@Override
	public String toBytecodeString(UniversalContext context) {
		return "Ljava/util/function/Function;";
//		throw new RuntimeException("TODO: Implement bytecode string for TypeLambda");
//		StringBuilder result = new StringBuilder("(");
//		params.stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).forEach(result::append);
//		result.append(")");
//		result.append(returnType.toBytecodeString(context));
//		return result.toString();
	}
	
	@Override
	public String toGenericBytecodeString(UniversalContext context) {
		throw new RuntimeException("TODO: Implement generic bytecode string for TypeLambda");
	}

	@Override
	public int loadInstruction() {
		return ALOAD;
	}
	
	@Override
	public int returnType() {
		return ARETURN;
	}

	@Override
	public boolean isGeneric() {
		return true;
	}
	
	@Override
	public int comparingInstruction() {
		return IF_ACMPEQ;
	}
	
	@Override
	public int negativeComparingInstruction() {
		return IF_ACMPNE;
	}

}
