package dev.jorel.fortelangprime.ast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.ast.types.TypingContext;
import dev.jorel.fortelangprime.parser.util.Pair;

public class RecordTypeDeclaration implements CodeableClass {
	
	private String name;
	private TypeRecord recordType;

	public RecordTypeDeclaration(String name, TypeRecord recordType) {
		this.name = name;
		this.recordType = recordType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public TypeRecord getType() {
		return this.recordType;
	}
	
	@Override
	public void emit(ClassWriter _unused, TypingContext context) {
		ClassWriter innerClassWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		
		innerClassWriter.visit(V1_8, ACC_PUBLIC, "Sample$" + name, null, "java/lang/Object", null);
		
		FieldVisitor fieldVisitor;
		
		for(Pair<String, Type> pair : recordType.getTypes()) {
			fieldVisitor = innerClassWriter.visitField(ACC_PUBLIC | ACC_SYNTHETIC, pair.first(), pair.second().toBytecodeString(), null, null);
			fieldVisitor.visitEnd();
		}
		
		
		
		{
			MethodVisitor methodVisitor = innerClassWriter.visitMethod(ACC_PUBLIC | ACC_SYNTHETIC, "<init>", "()V", null, null);
			methodVisitor.visitCode();
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
////			methodVisitor.visitInsn(ICONST_2);
////			methodVisitor.visitFieldInsn(PUTSTATIC, "Sample$1", "a", "I");
			methodVisitor.visitInsn(RETURN);
			methodVisitor.visitMaxs(1, 1);
			methodVisitor.visitEnd();
		}
		innerClassWriter.visitEnd();
		try {
			Files.write(new File("classfolder", "Sample$" + name +".class").toPath(), innerClassWriter.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
