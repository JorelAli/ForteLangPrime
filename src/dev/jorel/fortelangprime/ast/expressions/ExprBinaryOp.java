package dev.jorel.fortelangprime.ast.expressions;

import java.util.List;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.operation.CustomOperation;
import dev.jorel.fortelangprime.ast.operation.Operation;
import dev.jorel.fortelangprime.ast.operation.ShuntingYard;
import dev.jorel.fortelangprime.ast.operation.ShuntingYardable;
import dev.jorel.fortelangprime.ast.operation.StandardOperation;
import dev.jorel.fortelangprime.ast.types.InternalType;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeBool;
import dev.jorel.fortelangprime.ast.types.TypeDouble;
import dev.jorel.fortelangprime.ast.types.TypeInt;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

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
			case NE:
			case LE:
			case GE:
			case LT:
			case GT:
				return new TypeBool();
				
			case MULTIPLY:
			case DIVIDE:
			case ADD:
			case SUBTRACT:
			case POW:
			case MODULO:
				if(this.left.getType(context).getInternalType() == InternalType.INTEGER) {
					return new TypeInt();
				} else { 
					return new TypeDouble();
				}
			case PIPE2RIGHT:
				return this.right.getType(context);
			case PIPE2LEFT:
				return this.left.getType(context);
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			return operation.getReturnType(context);
		}
		System.out.println("BUSTED TYPE FOR BINOP: " + op);
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
			
			InternalType lit = binop.left.getType(context).getInternalType();
//			if(binop.right.getType(context) == null) {
//				System.out.println("Debugging weird garb: " + binop.right.toString());
//			}
			InternalType rit = binop.right.getType(context).getInternalType();
			
			boolean useDouble = lit == InternalType.DOUBLE && rit == InternalType.DOUBLE;
			
//			System.out.println(binop.left.getType(context) + " " + binop.op + " " +binop.right.getType(context));
			
			switch(operation) {
			case EQUALS:
				simpleEquality(methodVisitor, binop, context, false);
				break;
			case MULTIPLY:
				methodVisitor.visitInsn(useDouble ? DMUL : IMUL);
				break;
			case ADD:
				methodVisitor.visitInsn(useDouble ? DADD : IADD);
				break;
			case DIVIDE:
				methodVisitor.visitInsn(useDouble ? DDIV : IDIV);
				break;
			case POW:
				if(!useDouble) methodVisitor.visitInsn(I2D);
				methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Math", "pow", "(DD)D", false);
				if(!useDouble) methodVisitor.visitInsn(D2I);
				break;
			case SUBTRACT:
				methodVisitor.visitInsn(useDouble ? DSUB : ISUB);
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
			case GE:
				simpleJump(methodVisitor, IF_ICMPGE, false);
				break;
			case GT: 
				simpleJump(methodVisitor, IF_ICMPGT, false);
				break;
			case LE: 
				simpleJump(methodVisitor, IF_ICMPLE, false);
				break;
			case LT: 
				simpleJump(methodVisitor, IF_ICMPLT, false);
				break;
			case MODULO:
				methodVisitor.visitInsn(useDouble ? DREM : IREM);
				break;
			case NE:
				simpleEquality(methodVisitor, binop, context, true);
				break;
			case OR:
				methodVisitor.visitInsn(IOR);
				break;
			case PIPE2LEFT:
				// x |> f = f x.
				break;
			case PIPE2RIGHT:
				// f <| x = f x
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
	
	private void simpleEquality(MethodVisitor methodVisitor, ExprBinaryOp binop, UniversalContext context, boolean notEquals) {
		int jumpInsn = IFNE;
		
		if(binop.getLeft().getType(context).comparingInstruction() == IF_ACMPEQ) {
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "equals", "(Ljava/lang/Object;)Z", false);
		} else {
			jumpInsn = binop.getLeft().getType(context).comparingInstruction();
		}
		
		simpleJump(methodVisitor, jumpInsn, notEquals);
	}
	
	private void simpleJump(MethodVisitor methodVisitor, int jumpInsn, boolean inverted) {
		Label end = new Label();
		Label ifEqual = new Label();
		
		methodVisitor.visitJumpInsn(jumpInsn, ifEqual);
		methodVisitor.visitInsn(inverted ? ICONST_1 : ICONST_0);
		methodVisitor.visitJumpInsn(GOTO, end);
		
		methodVisitor.visitLabel(ifEqual);
		methodVisitor.visitInsn(inverted ? ICONST_0 : ICONST_1);
		
		methodVisitor.visitLabel(end);
	}

	@Override
	public int returnType(UniversalContext context) {
		if(op.isStandard()) {
			StandardOperation operation = (StandardOperation) op;
			switch(operation) {
			case MULTIPLY:
			case DIVIDE:
			case ADD:
			case SUBTRACT:
			case POW:
			case MODULO:
				if(this.left.getType(context).getInternalType() == InternalType.INTEGER) {
					return IRETURN;
				}  else {
					return DRETURN;
				}
//				return IRETURN;
			case ACCESSRECORD:
				return ((TypeRecord) this.left.getType(context)).getTypes()
					.stream()
					.filter(p -> p.first().equals(((ExprVariable) this.right).getName()))
					.findFirst().get().second().returnType();
			case EQUALS:
			case GT:
			case GE:
			case LT:
			case LE:
			case NE:
			case AND:
			case OR:
				return IRETURN;
			case PIPE2RIGHT:
				return this.right.getType(context).returnType();
			case PIPE2LEFT:
				return this.left.getType(context).returnType();
			default:
				break;
			}
		} else {
			CustomOperation operation = (CustomOperation) (op.isUnresolved() ? op.resolve(context) : op);
			return operation.returnType(context);
		}
		throw new RuntimeException("Invalid return type " + op);
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
