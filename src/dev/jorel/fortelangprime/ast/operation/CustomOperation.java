package dev.jorel.fortelangprime.ast.operation;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.CodeableClass;
import dev.jorel.fortelangprime.ast.CodeableMethod;
import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.util.Pair;

public class CustomOperation implements Operation, CodeableMethod, CodeableClass {
	
	private final int lineNumber;
	private final Associativity associativity;
	private final int precedence; // Higher precedence => more tightly binding
	private final String internalName; // Name of the function to generate
	private final String operatorToken;
	private final Pair<String, Type> leftType;
	private final Pair<String, Type> rightType;
	private final Type returnType;
	private final Expr body;
	
	public CustomOperation(int lineNumber, Associativity associativity, int precedence, String internalName, String operatorToken, Pair<String, Type> leftType, Pair<String, Type> rightType, Type returnType, Expr body) {
		this.lineNumber = lineNumber;
		this.associativity = associativity;
		this.precedence = precedence;
		this.internalName = internalName;
		this.operatorToken = operatorToken;
		this.leftType = leftType;
		this.rightType = rightType;
		this.returnType = returnType;
		this.body = body;
	}
	
	public int getPrecedence() {
		return this.precedence;
	}
	
	public Associativity getAssociativity() {
		return this.associativity;
	}
	
	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}
	
	public String getOperatorToken() {
		return this.operatorToken;
	}

	@Override
	public boolean isStandard() {
		return false;
	}
	
	public String getInternalName() {
		return this.internalName;
	}
	
	public Type getReturnType(UniversalContext context) {
		return this.returnType;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		body.emit(methodVisitor, context);
	}

	@Override
	public int returnType(UniversalContext context) {
		return returnType.returnType();
	}

	@Override
	public boolean isUnresolved() {
		return false;
	}

	@Override
	public Operation resolve(UniversalContext context) {
		return this;
	}
	
	public String getTypeDescriptor(UniversalContext context) {
		StringBuilder returnTypeString = new StringBuilder("(");
		returnTypeString.append(leftType.second().toBytecodeString(context));
		returnTypeString.append(rightType.second().toBytecodeString(context));
		returnTypeString.append(")");
		returnTypeString.append(returnType.toBytecodeString(context));
		return returnTypeString.toString();
	}

	@Override
	public void emit(ClassWriter classWriter, UniversalContext context) {
		MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, internalName, getTypeDescriptor(context), null, null);
		methodVisitor.visitCode();
		
		Label lineNumber = new Label();
		methodVisitor.visitLabel(lineNumber);
		methodVisitor.visitLineNumber(getLineNumber(), lineNumber);
		emit(methodVisitor, context);
		
		methodVisitor.visitInsn(returnType(context));
		
		Label variableTypes = new Label();
		methodVisitor.visitLabel(variableTypes);
		
		int index = 0;
		if(leftType.first() != null) {
			methodVisitor.visitLocalVariable(leftType.first(), leftType.second().toBytecodeString(context), null, lineNumber, variableTypes, index++);
		}
		if(rightType.first() != null) {
			methodVisitor.visitLocalVariable(rightType.first(), rightType.second().toBytecodeString(context), null, lineNumber, variableTypes, index++);
		}
		
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}

}
