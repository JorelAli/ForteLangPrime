import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Constructor;

import org.objectweb.asm.Attribute;


public class FLPTestLibrary implements Opcodes {

//	Library FLPTestLibrary {
//		export justTwo
//		export returnOneOrZero
//	} {
//
//		justTwo <Num> = 2;
//		
//		returnOneOrZero a<Bool> -> <Num> = if a then 1 else 0;
//		
//	}
	
	public static byte[] dump() {
		ClassWriter classWriter = new ClassWriter(0);
		MethodVisitor methodVisitor;

		classWriter.visit(V1_8, ACC_PUBLIC | ACC_INTERFACE, "FLPTestLibrary", null, "java/lang/Object", null);
		classWriter.visitSource("FLPTestLibrary.fl", "METADATA_GOES_HERE");

		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "returnOne", "()I", null, null);
			methodVisitor.visitCode();
			methodVisitor.visitInsn(ICONST_2);
			methodVisitor.visitInsn(IRETURN);
			methodVisitor.visitMaxs(1, 0);
			methodVisitor.visitEnd();
		}
		{
			
//			  public static returnOneOrZero(boolean) : int
//			   L0
//			    LINENUMBER 7 L0
//			    ILOAD 0   : a
//			    IFEQ L1
//			   L2
//			    LINENUMBER 8 L2
//			    ICONST_1
//			    IRETURN
//			   L1
//			    LINENUMBER 10 L1
//			    FRAME SAME
//			    ICONST_0
//			    IRETURN
//			   L3
//			    LOCALVARIABLE a boolean L0 L3 0
//			    MAXSTACK = 1
//			    MAXLOCALS = 1
//			  }

			
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "returnOneOrZero", "(Z)I", null, null);
			methodVisitor.visitCode();
			Label label0 = new Label();
			methodVisitor.visitLabel(label0);
			methodVisitor.visitLineNumber(7, label0);
			methodVisitor.visitVarInsn(ILOAD, 0);
			
			Label label1 = new Label();
			methodVisitor.visitJumpInsn(IFEQ, label1);
			
			Label label2 = new Label();
			methodVisitor.visitLabel(label2);
			methodVisitor.visitLineNumber(8, label2);
			methodVisitor.visitInsn(ICONST_1);
			methodVisitor.visitInsn(IRETURN);
			methodVisitor.visitLabel(label1);
			methodVisitor.visitLineNumber(10, label1);
			methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
			methodVisitor.visitInsn(ICONST_0);
			methodVisitor.visitInsn(IRETURN);
			
			Label label3 = new Label();
			methodVisitor.visitLabel(label3);
			methodVisitor.visitLocalVariable("a", "Z", null, label0, label3, 0);
			methodVisitor.visitMaxs(1, 1);
			methodVisitor.visitEnd();
		}
//			classWriter.visitEnd();
		
//		try {
//			Constructor<Attribute> constructor = Attribute.class.getDeclaredConstructor(String.class);
//			constructor.setAccessible(true);
//			Attribute attribute = (Attribute) constructor.newInstance("SourceDebugExtension");
////			attribute
//		} catch(Exception e) {}
//		
//		classWriter.visitAttribute(null);
		classWriter.visitEnd();

		return classWriter.toByteArray();
	}
	
	//Returns the required access for a method given whether it has been exported or not
	public int getAccess(boolean exported) {
		if(exported) {
			return ACC_PUBLIC | ACC_STATIC;
		} else {
			return ACC_PRIVATE | ACC_STATIC;
		}
	}
	
}
