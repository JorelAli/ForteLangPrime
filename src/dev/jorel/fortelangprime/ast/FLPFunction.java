package dev.jorel.fortelangprime.ast;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.expressions.Expr;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class FLPFunction implements CodeableClass {

	final String name;
	final Type returnType;
	final Type[] parameters;
	final Expr body;
	
	public FLPFunction(String name, Type[] paramTypes, Type returnType, Expr body) {
		this.name = name;
		this.parameters = paramTypes;
		this.returnType = returnType;
		this.body = body;
	}
	
	@Override
	public void emit(ClassWriter classWriter) {
		StringBuilder typeSignature = new StringBuilder("(");
		typeSignature.append(Arrays.stream(parameters).map(Type::toBytecodeString).collect(Collectors.joining(";")));
		typeSignature.append(")");
		
		try {
			//This is just temporary, trust me:
			typeSignature.append(body.getType(null).toBytecodeString());
		} catch (TypeException e) {
			e.printStackTrace();
		}
//		typeSignature.append(returnType.toBytecodeString());
		
		MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, name, typeSignature.toString(), null, null);
		methodVisitor.visitCode();
		body.emit(methodVisitor);
		methodVisitor.visitInsn(body.returnType());
		methodVisitor.visitMaxs(1, 0);
		methodVisitor.visitEnd();
	}
	
}
