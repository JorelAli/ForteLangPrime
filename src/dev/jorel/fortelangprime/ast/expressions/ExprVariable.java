package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.parser.util.Pair;

public class ExprVariable implements Expr {
	
	private int lineNumber;
	private String name;
	private String parentFunctionName;
	
	public ExprVariable(int lineNumber, String name, String parentFunctionName) {
		this.lineNumber = lineNumber;
		this.name = name;
		this.parentFunctionName = parentFunctionName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getParentFunctionName() {
		return this.parentFunctionName;
	}

	@Override
	public Type getType(UniversalContext context) {
		return context.getFunction(parentFunctionName).getParams().stream()
			.filter(p -> p.first().equals(name))
			.findFirst().get().second();
	}

	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		if(!context.getFunction(parentFunctionName).getParams().stream()
			.filter(p -> p.first().equals(name))
			.findFirst().isPresent()) {
			throw new TypeException("Parameter " + name + " has no locatable type in function " + parentFunctionName);
		}
		return getType(context);
	}

	@Override
	public boolean isReducable() {
		return false;
	}

	@Override
	public Expr substitute(String name, Expr val) {
		return this;
	}

	@Override
	public Expr deepCopy() {
		return new ExprVariable(lineNumber, name, parentFunctionName);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.VARIABLE;
	}
	
	public int getIndex(UniversalContext context) {
		int index = 0;
		for(Pair<String, Type> pair : context.getFunction(parentFunctionName).getParams()) {
			if(pair.first().equals(name)) {
				break;
			} else {
				index++;
			}
		}
		return index;
	}

	@Override
	public void emit(EmitterContext prog, MethodVisitor methodVisitor, UniversalContext context) {
		Type paramType = getType(context);
		methodVisitor.visitVarInsn(paramType.loadInstruction(), getIndex(context));
	}

	@Override
	public int returnType(UniversalContext context) {
		return getType(context).returnType();
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
