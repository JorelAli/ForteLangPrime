package dev.jorel.fortelangprime.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import dev.jorel.fortelangprime.ast.FLPFunction;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;
import dev.jorel.fortelangprime.parser.ParseException;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

/**
 * The main start point for compilation.
 */
public class FLPCompiler {
	
	public static void log(String message) {
		if(VERBOSE) System.out.println(message);
	}
	
	public static boolean VERBOSE = true;
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
	
	public FLPCompiler(File flpFile, File outputFolder, boolean verbose) {
		this.flpFile = flpFile;
		this.targetJavaVersion = JavaVersion.V_8;
		this.outputFolder = outputFolder;
	}
	
	public void compile() throws FileNotFoundException, ParseException, IOException, CompilationException, TypeException {
		FLPCompiler.log("Parsing file " + flpFile.toString());
		FLPLibrary lib = ForteLangPrimeParser.parse(flpFile);
		FLPCompiler.log("Parsing finished without any problems.\n");
		
		UniversalContext context = ForteLangPrimeParser.getUniversalContext();
		context.setLibraryName(lib.name);
		context.setJavaVersion(this.targetJavaVersion.getVersion());
		context.setOutputDir(outputFolder);
		
		if(context.doesExportAll()) {
			FLPCompiler.log("Exporting all declared functions");
		}
		
		for(FLPFunction f : lib.functions) {
			if(context.doesExportAll()) {
				f.setPublic();
			}
			FLPCompiler.log("Type checking function " + f.getName());
			f.getFunctionBody().typeCheck(context);
		}
		FLPCompiler.log("");
		
		BytecodeGenerator generator = new BytecodeGenerator(context, lib, this.targetJavaVersion);
		generator.compile();
		generator.writeToFile(this.outputFolder);
	}

}
