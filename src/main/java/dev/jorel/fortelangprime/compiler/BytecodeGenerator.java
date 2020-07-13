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
	
	private FLPLibrary lib;
	private UniversalContext context; 
	private int javaVersion;
	private byte[] compiledData;
	
	public BytecodeGenerator(UniversalContext context, FLPLibrary lib, JavaVersion version) {
		this.context = context;
		this.lib = lib;
		this.javaVersion = version.getVersion();
	}
	
	/**
	 * The main compilation endpoint
	 * @throws CompilationException 
	 */
	public void compile() throws CompilationException {

		//Applies the exports list
		for(FLPFunction f : lib.functions) {
			if(lib.exports.contains(f.getName())) {
				FLPCompiler.log("Exporting function " + f.getName());
				f.setPublic();
			}
		}
		
		FLPCompiler.log("Checking export list for invalid functions");
		Set<String> declaredFunctionNames = lib.functions.parallelStream().map(FLPFunction::getName).collect(Collectors.toSet());
		for(String str : lib.exports) {
			if(!declaredFunctionNames.contains(str)) {
				throw new CompilationException("Cannot export undeclared function " + str);
			}
		}
		
		this.compiledData = generateBytecode(null, lib);
	}
	
	public void writeToFile(File parentFolder) throws IOException {
		FLPCompiler.log("\nWriting class to file " + parentFolder + "/" + lib.name + ".class");
		Files.write(new File(parentFolder, lib.name + ".class").toPath(), compiledData);
	}
	
	private byte[] generateBytecode(String metadata, FLPLibrary lib) {
		String fileName = lib.name + ".flp"; 
		
		FLPCompiler.log("Compiling main class " + lib.name);
		ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		classWriter.visit(javaVersion, ACC_PUBLIC | ACC_ABSTRACT | ACC_INTERFACE, lib.name, null, "java/lang/Object", null);
		classWriter.visitSource(fileName, metadata);
		
		lib.thingsToEmit.forEach(e -> e.emit(classWriter, context));
			
		classWriter.visitEnd();
		return classWriter.toByteArray();
	}
}
