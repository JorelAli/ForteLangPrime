package dev.jorel.fortelangprime.ast;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.EmitterContext;
import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.util.Pair;

public class RecordTypeDeclaration implements CodeableClass {
	
	private String name;
	private TypeRecord recordType;
	boolean printable;

	/**
	 * 
	 * @param name
	 * @param recordType
	 * @param printable If true, adds a toString method
	 */
	public RecordTypeDeclaration(String name, TypeRecord recordType, boolean printable) {
		this.name = name;
		this.recordType = recordType;
		this.printable = printable;
	}
	
	public String getName() {
		return this.name;
	}
	
	public TypeRecord getType() {
		return this.recordType;
	}
	
	@Override
	public void emit(EmitterContext proj, ClassWriter parentClassWriter, TypingContext context) {
		String innerClassName = proj.getLibraryName() + "$" + name;

		parentClassWriter.visitInnerClass(innerClassName, proj.getLibraryName(), name, ACC_PUBLIC | ACC_STATIC);
		
		ClassWriter innerClassWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		innerClassWriter.visitInnerClass(innerClassName, proj.getLibraryName(), name, ACC_PUBLIC | ACC_STATIC);
		innerClassWriter.visit(V1_8, ACC_PUBLIC, innerClassName, null, "java/lang/Object", null);
		
		// Write public final fields
		FieldVisitor fieldVisitor;
		for(Pair<String, Type> pair : recordType.getTypes()) {
			fieldVisitor = innerClassWriter.visitField(ACC_PUBLIC | ACC_FINAL, pair.first(), pair.second().toBytecodeString(), null, null);
			fieldVisitor.visitEnd();
		}
		
		//Create default constructor
		{
			String signature = recordType.getTypes().stream().map(Pair::second).map(Type::toBytecodeString).collect(Collectors.joining());
			MethodVisitor methodVisitor = innerClassWriter.visitMethod(ACC_PUBLIC | ACC_SYNTHETIC, "<init>", "(" + signature + ")V", null, null);
			methodVisitor.visitCode();
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			
			int index = 1;
			for(Pair<String, Type> pair : recordType.getTypes()) {
				methodVisitor.visitVarInsn(ALOAD, 0);
				methodVisitor.visitVarInsn(pair.second().loadInstruction(), index++);
				methodVisitor.visitFieldInsn(PUTFIELD, innerClassName, pair.first(), pair.second().toBytecodeString());
			}
			methodVisitor.visitInsn(RETURN);
			methodVisitor.visitMaxs(1, 1);
			methodVisitor.visitEnd();
		}
		
		//Generate toString method
		if(printable) {
			
			String internalName = proj.getLibraryName() + "$" + name;
			
			MethodVisitor methodVisitor = innerClassWriter.visitMethod(ACC_PUBLIC, "toString", "()Ljava/lang/String;", null, null);
			methodVisitor.visitCode();
			
			methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
			methodVisitor.visitInsn(DUP);
			methodVisitor.visitLdcInsn("{ ");
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "(Ljava/lang/String;)V", false);
			
			List<Pair<String, Type>> types = recordType.getTypes();
			for (int i = 0; i < types.size(); i++) {
				Pair<String, Type> pair = types.get(i);
				
				methodVisitor.visitLdcInsn(pair.first() + " = ");
				methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
				
				methodVisitor.visitVarInsn(ALOAD, 0);
				methodVisitor.visitFieldInsn(GETFIELD, internalName, pair.first(), pair.second().toBytecodeString());
				methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + pair.second().toBytecodeString() + ")Ljava/lang/StringBuilder;", false);
				
//				if(i != types.size() - 1) {
				methodVisitor.visitLdcInsn(i != types.size() - 1 ? "; " : ";");
				methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//				}
			}
			methodVisitor.visitLdcInsn(" }<" + name + ">");
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
			
//			methodVisitor.visitVarInsn(ALOAD, 0);
//			methodVisitor.visitFieldInsn(GETFIELD, "A", "x", "I");
//			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(I)Ljava/lang/StringBuilder;", false);
//			methodVisitor.visitLdcInsn(", ");
//			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//			methodVisitor.visitVarInsn(ALOAD, 0);
//			methodVisitor.visitFieldInsn(GETFIELD, "A", "y", "Ljava/lang/String;");
//			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//			methodVisitor.visitLdcInsn(")");
//			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
//			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
			methodVisitor.visitInsn(ARETURN);
			
			
//			Label label0 = new Label();
//			methodVisitor.visitLabel(label0);
//			methodVisitor.visitLineNumber(12, label0);
//			methodVisitor.visitLdcInsn("");
//			methodVisitor.visitInsn(ARETURN);
//			Label label1 = new Label();
//			methodVisitor.visitLabel(label1);
//			methodVisitor.visitLocalVariable("this", "LE;", null, label0, label1, 0);
			methodVisitor.visitMaxs(1, 1);
			methodVisitor.visitEnd();
			
		}
		
		innerClassWriter.visitEnd();
		try {
			Files.write(new File("classfolder", innerClassName + ".class").toPath(), innerClassWriter.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
