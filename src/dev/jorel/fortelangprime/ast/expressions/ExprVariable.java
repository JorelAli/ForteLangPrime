package dev.jorel.fortelangprime.ast.expressions;

import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.InternalType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.parser.util.Pair;

public class ExprVariable implements Expr {
	
	private int lineNumber;
	private String name;
	private String parentFunctionName;
	private List<Expr> params;
	
	public ExprVariable(int lineNumber, String name, String parentFunctionName, List<Expr> params) {
		this.lineNumber = lineNumber;
		this.name = name;
		this.parentFunctionName = parentFunctionName;
		this.params = params;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getParentFunctionName() {
		return this.parentFunctionName;
	}

	@Override
	public Type getType(UniversalContext context) {
		Optional<Pair<String, Type>> localType = context.getFunction(parentFunctionName).getParams().stream()
			.filter(p -> p.first().equals(name)).findFirst();
		
		// Search local scope
		if(localType.isPresent()) {
			return localType.get().second();
		}
		
		// Search declared functions
		if(context.getFunction(name) != null) {
			return context.getFunction(name);
		}
		
		return null; //erm...
	}

	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		boolean functionFound = false;
		boolean inLocalScope = false;
		
		//Search declared functions
		if(context.getFunction(name) != null) {
			functionFound = true;
		}
		
		//Search local scope
		if(context.getFunction(parentFunctionName).getParams().stream()
			.filter(p -> p.first().equals(name))
			.findFirst().isPresent()) {
			inLocalScope = true;
		}
		
		if(functionFound || inLocalScope) {
			Type result = getType(context);
			if(result == null) {
				throw new TypeException("Type for variable " + lineNumber + " couldn't be found");
			}
			return result;
		} else {
			if(!inLocalScope) {
				throw new TypeException("Parameter " + name + " has no locatable type in function " + parentFunctionName);
			}
			if(!functionFound) {
				throw new TypeException("Function " + name + " can't be found in the file");
			}
			throw new TypeException("Honestly, I have no idea what happened here");
		}
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
		return new ExprVariable(lineNumber, name, parentFunctionName, params);
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
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		Type paramType = getType(context);
		
		if(paramType.getInternalType() == InternalType.FUNCTION) {
			TypeFunction tf = (TypeFunction) paramType;
			for(Expr e : params) {
				e.emit(methodVisitor, context);
			}
			methodVisitor.visitMethodInsn(INVOKESTATIC, context.getLibraryName(), name, tf.toBytecodeString(context), true);
		} else {
			methodVisitor.visitVarInsn(paramType.loadInstruction(), getIndex(context));
		}
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
