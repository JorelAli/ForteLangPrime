package dev.jorel.fortelangprime.ast.expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeNamedGeneric;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.util.Pair;
import dev.jorel.fortelangprime.util.StreamUtils;

//Constructing a full record (not using record updating { blah | x = 2 })
public class ExprRecordConstruction implements Expr {
	
	private int lineNumber;
	private Expr base;
	private List<Pair<String, Expr>> values;
	
	public ExprRecordConstruction(int lineNumber, Expr base, List<Pair<String, Expr>> values) {
		this.lineNumber = lineNumber;
		this.base = base;
		this.values = values;
	}

	@Override
	public Type getType(UniversalContext context) {
		List<Pair<String, Type>> types = new ArrayList<>();
		for(Pair<String, Expr> pair : values) {
			types.add(Pair.of(pair.first(), pair.second().getType(context)));
		}
//		types.stream().map(p -> p.first() + p.second().getInternalType()).forEach(System.out::println);
//		System.out.println();
		return context.getRecordTypeMatching(types);
	}

	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		
		if(base != null) {
			ExprVariable baseVar = (ExprVariable) base;
			baseVar.typeCheck(context);
			TypeNamedGeneric tng = (TypeNamedGeneric) context.getFunction(baseVar.getParentFunctionName()).getReturnType();
			TypeRecord tr = (TypeRecord) context.getRecordType(tng.getName());
			
			if(tr == null) {
				throw new TypeException("Record type has not been declared on line " + baseVar.getLineNumber());
			}
			
			// Yeah, I know a set would be better, but for pretty-printing it's better
			// (Also, this is a compiler, who cares about performance!)
			List<String> names = tr.getTypes().stream().map(Pair::first).collect(Collectors.toList());
			
			naming: for(Pair<String, Type> expectPair : tr.getTypes()) {
				for(Pair<String, Expr> pair : this.values) {
					if(expectPair.first() == null) {
						continue naming;
					}
					if(pair.first().equals(expectPair.first())) {
						if(expectPair.second().getInternalType() != pair.second().getType(context).getInternalType()) {
							throw new TypeException("Mismatched types on line " + lineNumber);
						}
					}
					if(!names.contains(pair.first())) {
						throw new TypeException("Parameter " + pair.first() + " is not present in { " + names.stream().collect(Collectors.joining(", ")) + " }");
					}
				}
			}
			
			return tr;
		} else {
			Type type = getType(context);
			if(type == null) {
				throw new TypeException("Error on line " +
					lineNumber + 
					" Record type { " + 
					values.stream().map(p -> p.first() + "<" + p.second().getType(context).getInternalType() + ">").collect(Collectors.joining("; ")) + " } has not been declared");
			}
			return type;
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
		return new ExprRecordConstruction(lineNumber, base, values);
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.INT_LITERAL;
	}

	private List<Pair<String, Expr>> reorderParams(UniversalContext context, List<Pair<String, Expr>> oldValues) {
		TypeRecord recordType = (TypeRecord) getType(context);
		List<String> orderedNames = recordType.getTypes().stream().map(Pair::first).collect(Collectors.toList()); 
		List<Pair<String, Expr>> newExprs = new ArrayList<>();
		naming: for(String str : orderedNames) {
			for(Pair<String, Expr> value : oldValues) {
				if(value.first().equals(str)) {
					newExprs.add(value);
					continue naming;
				}
			}
			newExprs.add(null);
		}
		
		return newExprs;
	}
	
	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		
		if(base != null) {
			if(base.getInternalType() == ExpressionType.VARIABLE) {
				ExprVariable baseVar = (ExprVariable) base;
				FLPCompiler.log("Emitting record update using " + baseVar.getName());
				TypeNamedGeneric tng = (TypeNamedGeneric) context.getFunction(baseVar.getParentFunctionName()).getReturnType();
				TypeRecord tr = (TypeRecord) context.getRecordType(tng.getName());
				
				FLPCompiler.log("Reordering record parameters to match type");
				List<String> orderedNames = tr.getTypes().stream().map(Pair::first).collect(Collectors.toList()); 
				List<Pair<String, Expr>> newExprs = new ArrayList<>();
				naming: for(String str : orderedNames) {
					for(Pair<String, Expr> value : this.values) {
						if(value.first().equals(str)) {
							newExprs.add(value);
							continue naming;
						}
					}
					newExprs.add(null);
				}
				FLPCompiler.log("Invoking base function");
				//TODO: This is only temporarily (), it will have to include function params later
				methodVisitor.visitMethodInsn(INVOKESTATIC, context.getLibraryName(), baseVar.getName(), "()L" + context.getLibraryName() + tr.toBytecodeString(context), true);
				methodVisitor.visitVarInsn(ASTORE, 0);
				
				FLPCompiler.log("Loading instance of " + tng.getName());
				methodVisitor.visitTypeInsn(NEW, context.getLibraryName() + "$" + tng.getName());
				methodVisitor.visitInsn(DUP);
				
				for (int i = 0; i < newExprs.size(); i++) {
					Pair<String, Expr> pair = newExprs.get(i);
					Pair<String, Type> expectedPair = tr.getTypes().get(i);
					if(pair == null) {
						FLPCompiler.log("Loading local parameter " + expectedPair.first() + " from base");
						methodVisitor.visitVarInsn(ALOAD, 0);
						methodVisitor.visitFieldInsn(GETFIELD, context.getLibraryName() + "$" + tng.getName(), expectedPair.first(), expectedPair.second().toBytecodeString(context));
					} else {
						FLPCompiler.log("Loading parameter " + pair.first());
						pair.second().emit(methodVisitor, context);
					}
				}
				
				String paramSignature = tr.getTypes().stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).collect(Collectors.joining());
				FLPCompiler.log("Calling constructor for " + tng.getName());
				methodVisitor.visitMethodInsn(INVOKESPECIAL, context.getLibraryName() + "$" + tng.getName(), "<init>", "(" + paramSignature + ")V", false);
//				methodVisitor.visitInsn(ARETURN);
//				methodVisitor.visitMaxs(0, 0);
//				methodVisitor.visitEnd();
			} else {
				//wtf?
			}
		} else {
			this.values = reorderParams(context, this.values);
			TypeRecord recordType = (TypeRecord) getType(context);
			String name; {
				List<Pair<String, Type>> types = new ArrayList<>();
				for(Pair<String, Expr> pair : values) {
					types.add(Pair.of(pair.first(), pair.second().getType(context)));
				}
				name = context.getNameOfRecordTypeMatching(types);
			}
			
			FLPCompiler.log("Loading instance of " + name);
			methodVisitor.visitTypeInsn(NEW, context.getLibraryName() + "$" + name);
			methodVisitor.visitInsn(DUP);
			FLPCompiler.log("Emitting values for record");
			values.forEach(exprPair -> {
				exprPair.second().emit(methodVisitor, context);
			});
			String paramSignature = recordType.getTypes().stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).collect(Collectors.joining());
			FLPCompiler.log("Calling constructor for " + name);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, context.getLibraryName() + "$" + name, "<init>", "(" + paramSignature + ")V", false);
//			methodVisitor.visitInsn(ARETURN);
//			methodVisitor.visitMaxs(0, 0);
//			methodVisitor.visitEnd();
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		return ARETURN;
	}

	@Override
	public int getLineNumber() {
		return this.lineNumber;
	}

}
