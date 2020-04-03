package dev.jorel.fortelangprime.ast.expressions;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeInt;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * Integers (e.g. 1, 230, -10)
 */
public class ExprIntLit implements Expr {
	
	private int lineNumber;
	private int value;
	
	public ExprIntLit(int lineNumber, int value) {
		this.lineNumber = lineNumber;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public Type getType(UniversalContext context) {
		return new TypeInt();
	}
	
	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
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
		return new ExprIntLit(lineNumber, value);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.INT_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		switch(value) {
			case -1:
				FLPCompiler.log("Emitting constant value -1");
				methodVisitor.visitInsn(ICONST_M1);
				break;
			case 0:
				FLPCompiler.log("Emitting constant value 0");
				methodVisitor.visitInsn(ICONST_0);
				break;
			case 1:
				FLPCompiler.log("Emitting constant value 1");
				methodVisitor.visitInsn(ICONST_1);
				break;
			case 2:
				FLPCompiler.log("Emitting constant value 2");
				methodVisitor.visitInsn(ICONST_2);
				break;
			case 3:
				FLPCompiler.log("Emitting constant value 3");
				methodVisitor.visitInsn(ICONST_3);
				break;
			case 4:
				FLPCompiler.log("Emitting constant value 4");
				methodVisitor.visitInsn(ICONST_4);
				break;
			case 5:
				FLPCompiler.log("Emitting constant value 5");
				methodVisitor.visitInsn(ICONST_5);
				break;
			default:
				if((byte) value == value) {
					FLPCompiler.log("Emitting byte value " + value);
					methodVisitor.visitIntInsn(BIPUSH, value);
				} else if((short) value == value) {
					FLPCompiler.log("Emitting short value " + value);
					methodVisitor.visitIntInsn(SIPUSH, value);
				} else {
					FLPCompiler.log("Emitting integer value " + value);
					methodVisitor.visitLdcInsn(Integer.valueOf(value));
				}
				break;
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		return IRETURN;
	}

	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}
