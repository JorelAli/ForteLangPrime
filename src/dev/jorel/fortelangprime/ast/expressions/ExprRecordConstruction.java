package dev.jorel.fortelangprime.ast.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
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
	public void emit(EmitterContext prog, MethodVisitor methodVisitor, TypingContext context) {
		
		TypeRecord recordType = (TypeRecord) getTypeUnsafe(context);
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
		
		methodVisitor.visitTypeInsn(NEW, prog.getLibraryName() + "$" + name);
		methodVisitor.visitInsn(DUP);
		values.forEach(exprPair -> {
			exprPair.second().emit(prog, methodVisitor, context);
		});
		String paramSignature = recordType.getTypes().stream().map(Pair::second).map(Type::toBytecodeString).collect(Collectors.joining());
		methodVisitor.visitMethodInsn(INVOKESPECIAL, prog.getLibraryName() + "$" + name, "<init>", "(" + paramSignature + ")V", false);
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
