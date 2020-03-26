package dev.jorel.fortelangprime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.FLPLibrary;
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
	
	public void compile() {
		
		for(FLPFunction f : lib.functions) {
			if(lib.exports.contains(f.getName())) {
				f.setPublic();
			}
		}
		
		this.compiledData = generateBytecode(lib.name + ".flp", null, lib.name, lib.functions);
	}
	
	public void writeToFile(File parentFolder) throws IOException {
		Files.write(new File(parentFolder, lib.name + ".class").toPath(), compiledData);
	}
	
	private byte[] generateBytecode(String fileName, String metadata, String libName, List<FLPFunction> functions) {
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classWriter.visit(javaVersion, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, libName, null, "java/lang/Object", null);
		classWriter.visitSource(fileName, metadata);
		for(FLPFunction f : functions) {
			f.emit(classWriter, context);
		}
		classWriter.visitEnd();
		return classWriter.toByteArray();
	}
	
}
