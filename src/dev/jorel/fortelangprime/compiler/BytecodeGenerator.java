package dev.jorel.fortelangprime.compiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.FLPLibrary;

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
	private UniversalContext context; 
	private int javaVersion;
	private byte[] compiledData;
	
	public BytecodeGenerator(UniversalContext context, FLPLibrary lib, JavaVersion version) {
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
		
		this.compiledData = generateBytecode(null, lib);
	}
	
	public void writeToFile(File parentFolder) throws IOException {
		Files.write(new File(parentFolder, lib.name + ".class").toPath(), compiledData);
	}
	
	private byte[] generateBytecode(String metadata, FLPLibrary lib) {
		String fileName = lib.name + ".flp"; 
		
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classWriter.visit(javaVersion, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, lib.name, null, "java/lang/Object", null);
		classWriter.visitSource(fileName, metadata);
		
		lib.thingsToEmit.forEach(e -> e.emit(classWriter, context));
			
		classWriter.visitEnd();
		return classWriter.toByteArray();
	}
}
