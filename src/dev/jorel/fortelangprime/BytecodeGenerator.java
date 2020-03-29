package dev.jorel.fortelangprime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.ast.RecordTypeDeclaration;
import dev.jorel.fortelangprime.ast.types.TypingContext;

public class BytecodeGenerator implements Opcodes {
	
	public enum JavaVersion {
		V_1(V1_1), V_2(V1_2), V_3(V1_3), V_4(V1_4), V_5(V1_5), V_6(V1_6), V_7(V1_7), V_8(V1_8), V_9(V9), V_10(V10), V_11(V11),
		V_12(V12), V_13(V13), V_14(V14);
		
		private int version;
		
		JavaVersion(int version) {
			this.version = version;
		}
	}
	
	private FLPLibrary lib;
	private TypingContext context; 
	private int javaVersion;
	private byte[] compiledData;
	
	public BytecodeGenerator(TypingContext context, FLPLibrary lib, JavaVersion version) {
		this.context = context;
		this.lib = lib;
		this.javaVersion = version.version;
	}
	
	/**
	 * The main compilation endpoint
	 * @throws CompilationException 
	 */
	public void compile() throws CompilationException {
		
		//Applies the exports list
		for(FLPFunction f : lib.functions) {
			if(lib.exports.contains(f.getName())) {
				f.setPublic();
			}
		}
		
		//Checks the exports list for invalid exported functions
		Set<String> declaredFunctionNames = lib.functions.parallelStream().map(FLPFunction::getName).collect(Collectors.toSet());
		for(String str : lib.exports) {
			if(!declaredFunctionNames.contains(str)) {
				throw new CompilationException("Cannot export undeclared function " + str);
			}
		}
		
		this.compiledData = generateBytecode(lib.name + ".flp", null, lib.name, lib.functions, lib.typeDeclarations);
	}
	
	public void writeToFile(File parentFolder) throws IOException {
		Files.write(new File(parentFolder, lib.name + ".class").toPath(), compiledData);
	}
	
	private byte[] generateBytecode(String fileName, String metadata, String libName, List<FLPFunction> functions, List<RecordTypeDeclaration> typeDecls) {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classWriter.visit(javaVersion, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, libName, null, "java/lang/Object", null);
		classWriter.visitSource(fileName, metadata);
		
//		inject(classWriter); //TODO: Remove this
		
		for(FLPFunction f : functions) {
			f.emit(classWriter, context);
		}
		
		for(RecordTypeDeclaration r : typeDecls) {
			r.emit(classWriter, context);
		}		
		
		inject2(classWriter);
		
		classWriter.visitEnd();
		return classWriter.toByteArray();
	}
	
	private void inject2(ClassWriter classWriter) {
		MethodVisitor methodVisitor;
		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "mk", "()LSample$Color;", null, null);
			methodVisitor.visitCode();
			methodVisitor.visitTypeInsn(NEW, "Sample$Color");
			methodVisitor.visitInsn(DUP);
			methodVisitor.visitInsn(ICONST_2);
			methodVisitor.visitInsn(ICONST_3);
			methodVisitor.visitInsn(ICONST_4);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "Sample$Color", "<init>", "(III)V", false);
//			methodVisitor.visitInsn(DUP);
//			methodVisitor.visitInsn(ICONST_2);
//			methodVisitor.visitFieldInsn(PUTFIELD, "Sample$Color", "red", "I");
			methodVisitor.visitInsn(ARETURN);
			methodVisitor.visitMaxs(0, 0);
			methodVisitor.visitEnd();
		}
	}
	
	private void inject(ClassWriter classWriter) {
		MethodVisitor methodVisitor;
		FieldVisitor fieldVisitor = classWriter.visitField(ACC_PUBLIC | ACC_STATIC | ACC_FINAL, "a", "I", null, null);
		fieldVisitor.visitEnd();
		
//		classWriter.visitNestMember("Sample$1");
//		classWriter.visitInnerClass("Sample$1", null, null, 0);

//		{
//			methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//			methodVisitor.visitCode();
//			Label label0 = new Label();
//			methodVisitor.visitLabel(label0);
//			methodVisitor.visitVarInsn(ALOAD, 0);
//			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
//			methodVisitor.visitInsn(RETURN);
//			Label label1 = new Label();
//			methodVisitor.visitLabel(label1);
//			methodVisitor.visitLocalVariable("this", "LAa;", null, label0, label1, 0);
//			methodVisitor.visitMaxs(1, 1);
//			methodVisitor.visitEnd();
//		}
		{
			methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "gen", "()LSample;", null, null);
			methodVisitor.visitCode();
			methodVisitor.visitInsn(ICONST_2);
			methodVisitor.visitFieldInsn(PUTSTATIC, "Sample", "a", "I");
//			methodVisitor.visitTypeInsn(CHECKCAST, "Sample");

			methodVisitor.visitTypeInsn(NEW, "java/lang/Object");
			methodVisitor.visitInsn(DUP);
			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
			methodVisitor.visitInsn(ARETURN);
			methodVisitor.visitMaxs(2, 0);
			methodVisitor.visitEnd();
		}
		
//		ClassWriter innerClassWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//		innerClassWriter.visit(javaVersion, ACC_PUBLIC, "Sample$1", null, "java/lang/Object", null);
//		{
//			methodVisitor = innerClassWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//			methodVisitor.visitCode();
//			methodVisitor.visitVarInsn(ALOAD, 0);
//			methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
////			methodVisitor.visitInsn(ICONST_2);
////			methodVisitor.visitFieldInsn(PUTSTATIC, "Sample$1", "a", "I");
//			methodVisitor.visitInsn(RETURN);
//			methodVisitor.visitMaxs(1, 1);
//			methodVisitor.visitEnd();
//		}
//		innerClassWriter.visitEnd();
//		try {
//			Files.write(new File("classfolder", "Sample$1.class").toPath(), innerClassWriter.toByteArray());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
//		classWriter.visitNestHost("Aa");
//
//		classWriter.visitOuterClass("Aa", "a", "()LAa;");
//
//		classWriter.visitInnerClass("Aa$1", null, null, 0);
//
//		{
//			methodVisitor = classWriter.visitMethod(0, "<init>", "()V", null, null);
//			methodVisitor.visitCode();
//			Label label0 = new Label();
//			methodVisitor.visitLabel(label0);
//			methodVisitor.visitVarInsn(ALOAD, 0);
//			methodVisitor.visitMethodInsn(INVOKESPECIAL, "Aa", "<init>", "()V", false);
//			Label label1 = new Label();
//			methodVisitor.visitLabel(label1);
//			methodVisitor.visitInsn(ICONST_2);
//			methodVisitor.visitFieldInsn(PUTSTATIC, "Aa$1", "a", "I");
//			Label label2 = new Label();
//			methodVisitor.visitLabel(label2);
//			methodVisitor.visitInsn(RETURN);
//			Label label3 = new Label();
//			methodVisitor.visitLabel(label3);
//			methodVisitor.visitLocalVariable("this", "LAa$1;", null, label0, label3, 0);
//			methodVisitor.visitMaxs(1, 1);
//			methodVisitor.visitEnd();
//		}
		classWriter.visitEnd();
	}
	
}
