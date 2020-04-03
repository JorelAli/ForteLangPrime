package dev.jorel.fortelangprime.util;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class LambdaInvocationHelper {

	/**
	 * Generates the invocation of a lambda that takes in one parameter
	 * and returns a parameter
	 * @param methodVisitor
	 * @param classOwner The owner of the class, such as Sample
	 * @param functionType The type of the function, such as (Ljava/lang/Integer;)Ljava/lang/Integer;
	 */
	public void generateLambdaAssignment(MethodVisitor methodVisitor, String classOwner, String functionType) {
		methodVisitor.visitInvokeDynamicInsn(
				"apply", 
				"()Ljava/util/function/Function;", 
				new Handle(Opcodes.H_INVOKESTATIC, 
						"java/lang/invoke/LambdaMetafactory", 
						"metafactory", 
						"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", 
						false), 
				new Object[] { Type.getType("(Ljava/lang/Object;)Ljava/lang/Object;"), 
						new Handle(Opcodes.H_INVOKESTATIC, 
								classOwner, 
								"lambda$0", 
								functionType, 
								false), 
						Type.getType(functionType)
					}
				);
	}
	
//	{
//		methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_STATIC | ACC_SYNTHETIC, "lambda$0", "(Ljava/lang/Integer;)Ljava/lang/Integer;", null, null);
//		methodVisitor.visitCode();
//		Label label0 = new Label();
//		methodVisitor.visitLabel(label0);
//		methodVisitor.visitVarInsn(ALOAD, 0);
//		methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
//		methodVisitor.visitInsn(ICONST_2);
//		methodVisitor.visitInsn(IADD);
//		methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
//		methodVisitor.visitInsn(ARETURN);
//		Label label1 = new Label();
//		methodVisitor.visitLabel(label1);
//		methodVisitor.visitLocalVariable("a", "Ljava/lang/Integer;", null, label0, label1, 0);
//		methodVisitor.visitMaxs(2, 1);
//		methodVisitor.visitEnd();
//		}
	
}
