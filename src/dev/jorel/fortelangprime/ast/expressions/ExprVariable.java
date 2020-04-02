package dev.jorel.fortelangprime.ast.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.operation.ShuntingYard;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable;
import dev.jorel.fortelangprime.ast.types.InternalType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeFunction;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.util.Pair;

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
	
	@Override
	public String toString() {
		return name + (params.isEmpty() ? "" : params); 
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<Expr> getParams() {
		return this.params;
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

	private void emitShuntingYard(MethodVisitor methodVisitor, UniversalContext context) {
		List<ShuntingYardable> tokens = new ArrayList<>();
//		tokens.add(new ExprVariable(lineNumber, name, parentFunctionName, new ArrayList<>()));
		for(Expr e : params) {
			if(e.getInternalType() == ExpressionType.BINARY_OPERATION) {
				tokens.addAll(ShuntingYard.flatten((ExprBinaryOp) e));
			} else {
				tokens.add(e);
			}
		}
		
		List<Expr> newExprs = new ArrayList<>();
		for(int i = 0; i < params.size(); i++) {
			newExprs.add((Expr) tokens.remove(0));
		}
		tokens.add(0, new ExprVariable(lineNumber, name, parentFunctionName, newExprs));
		
		tokens = ShuntingYard.doShuntingYard(tokens);
		for(ShuntingYardable e : tokens) {
			if(e instanceof ExprBinaryOp) {
				((ExprBinaryOp) e).emitOperationNew(methodVisitor, context, (ExprBinaryOp) e);
			} else {
				((Expr) e).emit(methodVisitor, context);
			}
		}
	}
	
	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		Type paramType = getType(context);
		
		for(Expr e : params) {
			if(e.getInternalType() == ExpressionType.BINARY_OPERATION) {
				emitShuntingYard(methodVisitor, context);
				return;
			}
		}
		
		if(paramType.getInternalType() == InternalType.FUNCTION) {
			FLPCompiler.log("Emitting function call " + name);
			TypeFunction tf = (TypeFunction) paramType;
			for(Expr e : params) {
				FLPCompiler.log("Emitting parameters for " + name + ": " + e.getInternalType());
				e.emit(methodVisitor, context);
			}
			FLPCompiler.log("Emitting function call invocation");
			methodVisitor.visitMethodInsn(INVOKESTATIC, context.getLibraryName(), name, tf.toBytecodeString(context), true);
		} else {
			int index = getIndex(context);
			if(paramType.getInternalType() == InternalType.DOUBLE) {
				FLPCompiler.log("Doubling index for double value");
				index *= 2;
			}
				
			FLPCompiler.log("Emitting parameter from location function (indexed: " + index + ")");
			methodVisitor.visitVarInsn(paramType.loadInstruction(), index);
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
