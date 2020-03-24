import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class Single3Dump implements Opcodes {
	
//	package pkg;
//	public interface Comparable extends Mesurable {
//		int LESS = -1;
//		int EQUAL = 0;
//		int GREATER = 1;
//		int compareTo(Object o);
//	}
	public static void example() {
		ClassWriter cw = new ClassWriter(0);
		cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE, "pkg/Comparable", null, "java/lang/Object",
				new String[] { "pkg/Mesurable" });
		cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();
		cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
		cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
		cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo", "(Ljava/lang/Object;)I", null, null).visitEnd();
		cw.visitEnd();
		byte[] b = cw.toByteArray();
		try {
			Files.write(new File("Comparable.class").toPath(), b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	


	public static byte[] dump() throws Exception {

		ClassWriter classWriter = new ClassWriter(0);

		classWriter.visit(V11, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, "Single3", null, "java/lang/Object", null);

//		classWriter.visitSource("Single3.java", null);

		{
			MethodVisitor methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "returnOne", "()I", null, null);
			methodVisitor.visitCode();
//			Label label0 = new Label();
//			methodVisitor.visitLabel(label0);
//			methodVisitor.visitLineNumber(50, label0);
			methodVisitor.visitInsn(ICONST_1);
			methodVisitor.visitInsn(IRETURN);
			methodVisitor.visitMaxs(1, 0);
			methodVisitor.visitEnd();
		}
		classWriter.visitEnd();

		return classWriter.toByteArray();
	}
}
