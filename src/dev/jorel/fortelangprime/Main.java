package dev.jorel.fortelangprime;
import static dev.jorel.fortelangprime.util.ConsoleColors.CYAN;
import static dev.jorel.fortelangprime.util.ConsoleColors.GREEN;
import static dev.jorel.fortelangprime.util.ConsoleColors.RESET;
import static dev.jorel.fortelangprime.util.ConsoleColors.TAB;

import java.io.File;

import dev.jorel.fortelangprime.compiler.FLPCompiler;

public class Main {
	
	/**
	 * Prints the help message. (Heavily inspired by Elm)
	 */
	public static void printHelp() {
//		StringBuilder t = new StringBuilder();
//		for(int i = 0; i < 80; i++) t.append(" ");
//		System.out.println(t.toString() + "| <-- Cutoff");
		System.out.println("Welcome to the ForteLang\u2032 compiler! To compile stuff, provide me some files:");
		System.out.println();
		System.out.println(TAB + CYAN + "flp <zero-or-more-flp-files>" + RESET);
		System.out.println();
		System.out.println("For example:");
		System.out.println();
		System.out.println(TAB + GREEN + "flp MyLibrary.flp" + RESET);
		System.out.println();
		System.out.println("This tries to compile a ForteLang\u2032 library, generating the respective class");
		System.out.println("files for the input library");
		System.out.println();
		System.out.println("You can customize this command with the following flags:");
		System.out.println();
		System.out.println(TAB + CYAN + "--outputDir=<output-directory>" + RESET);
		System.out.println(TAB + TAB + "Compiles a library into the provided output directory. For example");
		System.out.println(TAB + TAB + "--outputDir=classpath/ to compile into the ./classpath directory.");
		System.out.println();
		System.out.println(TAB + CYAN + "--javaVersion=<java-version>" + RESET);
		System.out.println(TAB + TAB + "Compiles a library for a specific Java version. For example");
		System.out.println(TAB + TAB + "--javaVersion=1.8 to compile classes for Java 1.8 and higher or");
		System.out.println(TAB + TAB + "--javaVersion=9 to compile classes for Java 9 and higher. By default, we");
		System.out.println(TAB + TAB + "use Java version 1.8!");
		System.out.println();
		System.out.println(TAB + CYAN + "--verbose" + RESET);
		System.out.println(TAB + TAB + "Outputs message about what the compiler is doing. There's a lot of");
		System.out.println(TAB + TAB + "messages output by the compiler, you've been warned!");
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length == 0) {
			printHelp();
			return;
		}
		
		File outputDir = new File(".");
		
		// Handle flags
		for(String str : args) {
			String s = str.toLowerCase();
			
			if(s.startsWith("--help")) {
				printHelp();
				return;
			}
			
			if(s.startsWith("--javaVersion=")) {
				
			}
			
			if(s.startsWith("--outputDir=")) {
				outputDir = new File(str.substring(12));
			}
			
			if(s.startsWith("--verbose=")) {
				FLPCompiler.VERBOSE = true;
			}
		}
		
		for(String str : args) {
			if(!str.startsWith("--")) {
				new FLPCompiler(new File(str), outputDir).compile();
			}
		}
	}
}
