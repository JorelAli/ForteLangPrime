package dev.jorel.fortelangprime.ast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import dev.jorel.fortelangprime.ast.types.Type;
import dev.jorel.fortelangprime.ast.types.TypeRecord;
import dev.jorel.fortelangprime.compiler.UniversalContext;
import dev.jorel.fortelangprime.util.Pair;
import dev.jorel.fortelangprime.util.StreamUtils;

public class RecordTypeDeclaration implements CodeableClass {
	
	private String name;
	private TypeRecord recordType;
	boolean printable;
	boolean equatable;

	/**
	 * 
	 * @param name
	 * @param recordType
	 * @param printable If true, adds a toString method
	 */
	public RecordTypeDeclaration(String name, TypeRecord recordType, boolean printable, boolean equatable) {
		this.name = name;
		this.recordType = recordType;
		this.printable = printable;
		this.equatable = equatable;
	}
	
	public String getName() {
		return this.name;
	}
	
	public TypeRecord getType() {
		return this.recordType;
	}
	
	@Override
	public void emit(ClassWriter parentClassWriter, UniversalContext context) {
		String innerClassName = context.getLibraryName() + "$" + name;

		parentClassWriter.visitInnerClass(innerClassName, context.getLibraryName(), name, ACC_PUBLIC | ACC_STATIC);
		
		ClassWriter innerClassWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		innerClassWriter.visitInnerClass(innerClassName, context.getLibraryName(), name, ACC_PUBLIC | ACC_STATIC);
		innerClassWriter.visit(V1_8, ACC_PUBLIC, innerClassName, null, "java/lang/Object", null);
		
		// Write public final fields
		FieldVisitor fieldVisitor;
		for(Pair<String, Type> pair : recordType.getTypes()) {
			fieldVisitor = innerClassWriter.visitField(ACC_PUBLIC | ACC_FINAL, pair.first(), pair.second().toBytecodeString(context), null, null);
			fieldVisitor.visitEnd();
		}
		
		//Create default constructor
		{
			String signature = recordType.getTypes().stream().map(Pair::second).map(StreamUtils.with(Type::toBytecodeString, context)).collect(Collectors.joining());
			MethodVisitor methodVisitor = innerClassWriter.visitMethod(ACC_PUBLIC | ACC_SYNTHETIC, "<init>", "(" + signature + ")V", null, null);
			methodVisitor.visitCode();
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			
			int index = 1;
			for(Pair<String, Type> pair : recordType.getTypes()) {
				methodVisitor.visitVarInsn(ALOAD, 0);
				methodVisitor.visitVarInsn(pair.second().loadInstruction(), index++);
				methodVisitor.visitFieldInsn(PUTFIELD, innerClassName, pair.first(), pair.second().toBytecodeString(context));
			}
			methodVisitor.visitInsn(RETURN);
			methodVisitor.visitMaxs(1, 1);
			methodVisitor.visitEnd();
		}
		
		//Generate toString method
		if(printable) {
			emitPrintable(innerClassWriter, context);
		}
		
		//Generate equals method
		if(equatable) {
			emitEquatable(innerClassWriter, context);
		}
		
		innerClassWriter.visitEnd();
		try {
			Files.write(new File("classfolder", innerClassName + ".class").toPath(), innerClassWriter.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void emitEquatable(ClassWriter innerClassWriter, UniversalContext context) {		
		MethodVisitor methodVisitor = innerClassWriter.visitMethod(ACC_PUBLIC, "equals", "(Ljava/lang/Object;)Z", null, null);
		methodVisitor.visitCode();
		
		//this == obj
		Label keepGoing = new Label();
		methodVisitor.visitVarInsn(ALOAD, 0);
		methodVisitor.visitVarInsn(ALOAD, 1);
		methodVisitor.visitJumpInsn(IF_ACMPNE, keepGoing);
		
		Label earlyReturn = new Label();
		methodVisitor.visitLabel(earlyReturn);
		methodVisitor.visitInsn(ICONST_1);
		methodVisitor.visitInsn(IRETURN);
		
		methodVisitor.visitLabel(keepGoing);
		
		//if obj == null
		keepGoing = new Label();
		methodVisitor.visitVarInsn(ALOAD, 1);
		methodVisitor.visitJumpInsn(IFNONNULL, keepGoing);
		
		earlyReturn = new Label();
		methodVisitor.visitLabel(earlyReturn);
		methodVisitor.visitInsn(ICONST_0);
		methodVisitor.visitInsn(IRETURN);
		
		methodVisitor.visitLabel(keepGoing);
		keepGoing = new Label();

		//this.class == that.class
		earlyReturn = new Label();
		methodVisitor.visitVarInsn(ALOAD, 0);
		methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
		methodVisitor.visitVarInsn(ALOAD, 1);
		methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Object", "getClass", "()Ljava/lang/Class;", false);
		methodVisitor.visitJumpInsn(IF_ACMPEQ, keepGoing);
		
		earlyReturn = new Label();
		methodVisitor.visitLabel(earlyReturn);
		methodVisitor.visitInsn(ICONST_0);
		methodVisitor.visitInsn(IRETURN);
		
		methodVisitor.visitLabel(keepGoing);
		keepGoing = new Label();

		//A a = (A) obj
		String internalName = context.getLibraryName() + "$" + name;
		methodVisitor.visitVarInsn(ALOAD, 1);
		methodVisitor.visitTypeInsn(CHECKCAST, internalName);
		methodVisitor.visitVarInsn(ASTORE, 2);
		
		//Check params
		for(Pair<String, Type> pair : recordType.getTypes()) {
			earlyReturn = new Label();
			methodVisitor.visitVarInsn(ALOAD, 0);
			methodVisitor.visitFieldInsn(GETFIELD, internalName, pair.first(), pair.second().toBytecodeString(context));
			methodVisitor.visitVarInsn(ALOAD, 2);
			methodVisitor.visitFieldInsn(GETFIELD, internalName, pair.first(), pair.second().toBytecodeString(context));
			methodVisitor.visitJumpInsn(pair.second().comparingInstruction(), keepGoing);
			
			methodVisitor.visitLabel(earlyReturn);
			methodVisitor.visitInsn(ICONST_0);
			methodVisitor.visitInsn(IRETURN);
			
			methodVisitor.visitLabel(keepGoing);
			keepGoing = new Label();
		}
		
		//return true;
		methodVisitor.visitInsn(ICONST_1);
		methodVisitor.visitInsn(IRETURN);
		methodVisitor.visitMaxs(1, 1);
		methodVisitor.visitEnd();
	}

	private void emitPrintable(ClassWriter innerClassWriter, UniversalContext context) {
		String internalName = context.getLibraryName() + "$" + name;
		
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
			methodVisitor.visitFieldInsn(GETFIELD, internalName, pair.first(), pair.second().toBytecodeString(context));
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(" + pair.second().toBytecodeString(context) + ")Ljava/lang/StringBuilder;", false);
			
			methodVisitor.visitLdcInsn(i != types.size() - 1 ? "; " : ";");
			methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		}
		methodVisitor.visitLdcInsn(" }<" + name + ">");
		methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
		methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
		methodVisitor.visitInsn(ARETURN);
		methodVisitor.visitMaxs(1, 1);
		methodVisitor.visitEnd();
	}
	
}
