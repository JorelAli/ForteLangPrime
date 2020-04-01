package dev.jorel.fortelangprime.ast.expressions;

import java.util.List;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.operation.CustomOperation;
import dev.jorel.fortelangprime.ast.operation.Operation;
import dev.jorel.fortelangprime.ast.operation.ShuntingYard;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable;
import dev.jorel.fortelangprime.ast.operation.StandardOperation;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.ast.types.TypeInt;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;
import dev.jorel.fortelangprime.util.Pair;

public class ExprBinaryOp implements Expr {
	
	private int lineNumber;
	private Expr left;
	private Expr right;
	private Operation op;
	private boolean hasBrackets;
	
	public ExprBinaryOp(int lineNumber, Expr left, Expr right, Operation op, boolean hasBrackets) {
		this.lineNumber = lineNumber;
		this.left = left;
		this.right = right;
		this.op = op;
		this.hasBrackets = hasBrackets;
	}
	
	public Operation getOperation() {
		return this.op;
	}
	
	public Expr getLeft() {
		return this.left;
	}
	
	public Expr getRight() {
		return this.right;
	}
	
	public boolean hasBrackets() {
		return this.hasBrackets;
	}

	@Override
	public Type getType(UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
				return new TypeBool();
			case MULTIPLY:
				return new TypeInt(); //TODO: TypeInt for now, won't be for long
			default:
				break;
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			return operation.getReturnType(context);
		}
		
		return null;
	}
	
	@Override
	public Type typeCheck(UniversalContext context) throws TypeException {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
				if(left.typeCheck(context).getInternalType() != right.typeCheck(context).getInternalType()) {
					throw new TypeException("Left is " + left.getType(context).getInternalType() + " but right is " + right.getType(context).getInternalType());
				}
				return new TypeBool();
			case DIVIDE:
			case ADD:
			case SUBTRACT:
			case POW:
			case MULTIPLY:
				return new TypeInt();
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			// TODO: Properly type check custom operators
			//Check that the operator has been declared!
			return operation.getReturnType(context);
		}
		return null;		
	}

	@Override
	public ExpressionType getInternalType() {
		return ExpressionType.BINARY_OPERATION;
	}

	@Override
	public boolean isReducable() {
		return true;
	}

	@Override
	public Expr substitute(String name, Expr val) {
		return this;
	}

	@Override
	public Expr deepCopy() {
		return new ExprBinaryOp(lineNumber, left, right, op, hasBrackets);
	}

	@Override
	public void emit(MethodVisitor methodVisitor, UniversalContext context) {
		List<ShuntingYardable> es = ShuntingYard.doShuntingYard(ShuntingYard.flatten(this));
		for (int i = 0; i < es.size(); i++) {
			ShuntingYardable e = es.get(i);
			if(e instanceof ExprBinaryOp) {
				emitOperationNew(methodVisitor, context, (ExprBinaryOp) e);
			} else {
				((Expr) e).emit(methodVisitor, context);
			}
		}	
	}
	
	public void emitOperationNew(MethodVisitor methodVisitor, UniversalContext context, ExprBinaryOp binop) {
		Operation op = binop.getOperation();
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			FLPCompiler.log("Emitting standard operation " + operation.name());
			switch(operation) {
				case EQUALS: {
//					binop.left.emit(methodVisitor, context);
//					binop.right.emit(methodVisitor, context);
					Label end = new Label();
					Label ifEqual = new Label();
					
					if(binop.getLeft().getType(context).comparingInstruction() == IF_ACMPEQ) {
						methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
						methodVisitor.visitJumpInsn(IFNE, ifEqual);
					} else {
						methodVisitor.visitJumpInsn(binop.getLeft().getType(context).comparingInstruction(), ifEqual);
					}
					methodVisitor.visitInsn(ICONST_0);
					methodVisitor.visitJumpInsn(GOTO, end);
					
					methodVisitor.visitLabel(ifEqual);
					methodVisitor.visitInsn(ICONST_1);
					
					methodVisitor.visitLabel(end);
					break;
				}
				case MULTIPLY: {
					methodVisitor.visitInsn(IMUL);
					break;
				}
			case ADD:
				methodVisitor.visitInsn(IADD);
				break;
			case DIVIDE:
				methodVisitor.visitInsn(IDIV);
				break;
			case POW:
				methodVisitor.visitInsn(I2D);
				methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "pow", "(DD)D", false);
				methodVisitor.visitInsn(D2I);
				break;
			case SUBTRACT:
				methodVisitor.visitInsn(ISUB);
				break;
			case ACCESSRECORD:
				
				ExprVariable exprVar = (ExprVariable) binop.right;
				TypeRecord leftType = (TypeRecord) binop.left.getType(context);
				String classOwner = context.getLibraryName() + "$" + leftType.getName();
				String descriptor = leftType.getTypes().stream().filter(p -> p.first().equals(exprVar.getName())).findFirst().get().second().toBytecodeString(context);
				methodVisitor.visitFieldInsn(GETFIELD, classOwner, exprVar.getName(), descriptor);
				break;
			case AND:
				methodVisitor.visitInsn(IAND);
				break;
			case CONCATENATE:
				break;
			case CONS:
				break;
			case GE: {
				Label end = new Label();
				Label ifEqual = new Label();
				
				methodVisitor.visitJumpInsn(IF_ICMPGE, ifEqual);
				methodVisitor.visitInsn(ICONST_0);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_1);
				
				methodVisitor.visitLabel(end);
				break;
			}
			case GT: {
				Label end = new Label();
				Label ifEqual = new Label();
				
				methodVisitor.visitJumpInsn(IF_ICMPGT, ifEqual);
				methodVisitor.visitInsn(ICONST_0);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_1);
				
				methodVisitor.visitLabel(end);
				break;
			}
			case LE: {
				Label end = new Label();
				Label ifEqual = new Label();
				
				methodVisitor.visitJumpInsn(IF_ICMPLE, ifEqual);
				methodVisitor.visitInsn(ICONST_0);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_1);
				
				methodVisitor.visitLabel(end);
				break;
			}
			case LT: {
				Label end = new Label();
				Label ifEqual = new Label();
				
				methodVisitor.visitJumpInsn(IF_ICMPLT, ifEqual);
				methodVisitor.visitInsn(ICONST_0);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_1);
				
				methodVisitor.visitLabel(end);
				break;
			}
			case MODULO:
				methodVisitor.visitInsn(IREM);
				break;
			case NE:
				Label end = new Label();
				Label ifEqual = new Label();
				
				if(binop.getLeft().getType(context).comparingInstruction() == IF_ACMPEQ) {
					methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
					methodVisitor.visitJumpInsn(IFNE, ifEqual);
				} else {
					methodVisitor.visitJumpInsn(binop.getLeft().getType(context).comparingInstruction(), ifEqual);
				}
				methodVisitor.visitInsn(ICONST_1);
				methodVisitor.visitJumpInsn(GOTO, end);
				
				methodVisitor.visitLabel(ifEqual);
				methodVisitor.visitInsn(ICONST_0);
				
				methodVisitor.visitLabel(end);
				break;
			case OR:
				methodVisitor.visitInsn(IOR);
				break;
			case PIPE2LEFT:
				break;
			case PIPE2RIGHT:
				break;
			default:
				break;
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			FLPCompiler.log("Emitting custom operation " + operation.getOperatorToken());
			methodVisitor.visitMethodInsn(INVOKESTATIC, context.getLibraryName(), operation.getInternalName(), operation.getTypeDescriptor(context), true);
		}
	}
	
	
	
	private void emitOperation(MethodVisitor methodVisitor, UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			FLPCompiler.log("Emitting standard operation " + operation.name());
			switch(operation) {
				case EQUALS: {
					left.emit(methodVisitor, context);
					right.emit(methodVisitor, context);
					Label end = new Label();
					Label ifEqual = new Label();
					
					if(left.getType(context).comparingInstruction() == IF_ACMPEQ) {
						methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
						methodVisitor.visitJumpInsn(IFNE, ifEqual);
					} else {
						methodVisitor.visitJumpInsn(left.getType(context).comparingInstruction(), ifEqual);
					}
					methodVisitor.visitInsn(ICONST_0);
					methodVisitor.visitJumpInsn(GOTO, end);
					
					methodVisitor.visitLabel(ifEqual);
					methodVisitor.visitInsn(ICONST_1);
					
					methodVisitor.visitLabel(end);
					break;
				}
				case MULTIPLY: {
					left.emit(methodVisitor, context);
					right.emit(methodVisitor, context);
					methodVisitor.visitInsn(IMUL);
					break;
				}
			case ADD:
				left.emit(methodVisitor, context);
				right.emit(methodVisitor, context);
				methodVisitor.visitInsn(IADD);
				break;
			case DIVIDE:
				left.emit(methodVisitor, context);
				right.emit(methodVisitor, context);
				methodVisitor.visitInsn(IDIV);
				break;
			case POW:
				left.emit(methodVisitor, context);
				methodVisitor.visitInsn(I2D);
				right.emit(methodVisitor, context);
				methodVisitor.visitInsn(I2D);
				methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "pow", "(DD)D", false);
				methodVisitor.visitInsn(D2I);
				break;
			case SUBTRACT:
				left.emit(methodVisitor, context);
				right.emit(methodVisitor, context);
				methodVisitor.visitInsn(ISUB);
				break;
			default:
				break;
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			FLPCompiler.log("Emitting custom operation " + operation.getOperatorToken());
			left.emit(methodVisitor, context);
			right.emit(methodVisitor, context);
			methodVisitor.visitMethodInsn(INVOKESTATIC, context.getLibraryName(), operation.getInternalName(), operation.getTypeDescriptor(context), true);
//			System.out.println("TODO: emit binop custom op");
		}
	}

	@Override
	public int returnType(UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case EQUALS:
			case MULTIPLY:
			case DIVIDE:
			case ADD:
			case SUBTRACT:
			case POW:
				return IRETURN;
			case ACCESSRECORD:
				return ((TypeRecord) this.left.getType(context)).getTypes()
					.stream()
					.filter(p -> p.first().equals(((ExprVariable) this.right).getName()))
					.findFirst().get().second().returnType();
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			return operation.returnType(context);
		}
//		return IRETURN;
		throw new RuntimeException("Invalid return type " + op);
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
