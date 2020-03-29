package dev.jorel.fortelangprime.ast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

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
	public void emit(ClassWriter parentClassWriter, TypingContext context) {
		String innerClassName = "Sample$" + name;

		parentClassWriter.visitInnerClass(innerClassName, "Sample", name, ACC_PUBLIC | ACC_STATIC);
		
		ClassWriter innerClassWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		innerClassWriter.visitInnerClass(innerClassName, "Sample", name, ACC_PUBLIC | ACC_STATIC);
		innerClassWriter.visit(V1_8, ACC_PUBLIC, innerClassName, null, "java/lang/Object", null);
		
		FieldVisitor fieldVisitor;
		
		for(Pair<String, Type> pair : recordType.getTypes()) {
			fieldVisitor = innerClassWriter.visitField(ACC_PUBLIC | ACC_FINAL | ACC_SYNTHETIC, pair.first(), pair.second().toBytecodeString(), null, null);
			fieldVisitor.visitEnd();
		}
		
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
			
			
//			methodVisitor.visitVarInsn(ILOAD, 1);
//			methodVisitor.visitFieldInsn(PUTFIELD, "Sample$" + name, "red", "I");
//			methodVisitor.visitVarInsn(ALOAD, 0);
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
