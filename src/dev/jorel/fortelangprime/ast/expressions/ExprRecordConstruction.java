package dev.jorel.fortelangprime.ast.expressions;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.enums.ExpressionType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.parser.util.Pair;

//Constructing a full record (not using record updating { blah | x = 2 })
public class ExprRecordConstruction implements Expr {
	
	private int lineNumber;
	private List<Pair<String, Expr>> values;
	
	public ExprRecordConstruction(int lineNumber, List<Pair<String, Expr>> values) {
		this.lineNumber = lineNumber;
		this.values = values;
	}

	@Override
	public Type getType(TypingContext context) throws TypeException {
		List<Pair<String, Type>> types = new ArrayList<>();
		for(Pair<String, Expr> pair : values) {
			types.add(Pair.of(pair.first(), pair.second().getType(context)));
		}
		return context.getRecordTypeMatching(types);
	}
	
	private Type getTypeUnsafe(TypingContext context) {
		List<Pair<String, Type>> types = new ArrayList<>();
		for(Pair<String, Expr> pair : values) {
			try {
				types.add(Pair.of(pair.first(), pair.second().getType(context)));
			} catch (TypeException e) {
				e.printStackTrace();
			}
		}
		return context.getRecordTypeMatching(types);
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
		return new ExprRecordConstruction(lineNumber, values);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.INT_LITERAL;
	}

	@Override
	public void emit(MethodVisitor methodVisitor, TypingContext context) {
		
		TypeRecord rt = (TypeRecord) getTypeUnsafe(context);
		String name; {
			List<Pair<String, Type>> types = new ArrayList<>();
			for(Pair<String, Expr> pair : values) {
				try {
					types.add(Pair.of(pair.first(), pair.second().getType(context)));
				} catch (TypeException e) {
					e.printStackTrace();
				}
			}
			name = context.getNameOfRecordTypeMatching(types);
		}
		
		System.out.println(name + "<<");
		
		//TODO
		methodVisitor.visitTypeInsn(NEW, "Sample$Color");
		methodVisitor.visitInsn(DUP);
		values.forEach(a -> {
			System.out.println(a);
			a.second().emit(methodVisitor, context);
		});
//		methodVisitor.visitInsn(ICONST_2);
//		methodVisitor.visitInsn(ICONST_3);
//		methodVisitor.visitInsn(ICONST_4);
		methodVisitor.visitMethodInsn(INVOKESPECIAL, "Sample$Color", "<init>", "(III)V", false);
		methodVisitor.visitInsn(ARETURN);
		methodVisitor.visitMaxs(0, 0);
		methodVisitor.visitEnd();
	}

	@Override
	public int returnType(TypingContext context) {
		return ARETURN;
	}

	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}
