package dev.jorel.fortelangprime.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.compiler.BytecodeGenerator.JavaVersion;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;
import dev.jorel.fortelangprime.parser.ParseException;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * The main start point for compilation.
 */
public class FLPCompiler {
	
	private File flpFile;
	private JavaVersion targetJavaVersion;
	private File outputFolder;
	
	public FLPCompiler(File flpFile, JavaVersion targetJavaVersion, File outputFolder) {
		this.flpFile = flpFile;
		this.targetJavaVersion = targetJavaVersion;
		this.outputFolder = outputFolder;
	}
	
	public FLPCompiler(File flpFile, File outputFolder) {
		this.flpFile = flpFile;
		this.targetJavaVersion = JavaVersion.V_8;
		this.outputFolder = outputFolder;
	}
	
	public void compile() throws FileNotFoundException, ParseException, IOException, CompilationException, TypeException {
		FLPLibrary lib = ForteLangPrimeParser.parse(flpFile);
		
		UniversalContext context = ForteLangPrimeParser.getUniversalContext();
		for(FLPFunction f : lib.functions) {
			f.getFunctionBody().typeCheck(context);
		}
		
		BytecodeGenerator generator = new BytecodeGenerator(context, lib, this.targetJavaVersion);
		generator.compile();
		generator.writeToFile(this.outputFolder);
	}

}
