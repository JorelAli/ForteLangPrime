package dev.jorel.fortelangprime;
import static dev.jorel.fortelangprime.util.ConsoleColors.CYAN;
import static dev.jorel.fortelangprime.util.ConsoleColors.GREEN;
import static dev.jorel.fortelangprime.util.ConsoleColors.RED;
import static dev.jorel.fortelangprime.util.ConsoleColors.RESET;
import static dev.jorel.fortelangprime.util.ConsoleColors.TAB;
import static dev.jorel.fortelangprime.util.ConsoleColors.YELLOW;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.util.Levenshtein;
import dev.jorel.fortelangprime.util.Pair;

public class Main {
	
	/**
	 * Prints the help message. (Heavily inspired by Elm)
	 */
	private static void printHelp() {
//		StringBuilder t = new StringBuilder();
//		for(int i = 0; i < 80; i++) t.append(" ");
//		System.out.println(t.toString() + "| <-- Cutoff");
		System.out.println("Welcome to the ForteLang\u2032 compiler! To compile stuff, provide me some files:");
		System.out.println();
		System.out.println(TAB + CYAN + "java -jar flpc.jar <zero-or-more-flp-files>" + RESET);
		System.out.println();
		System.out.println("For example:");
		System.out.println();
		System.out.println(TAB + GREEN + "java -jar flpc.jar MyLibrary.flp" + RESET);
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
//		System.out.println(TAB + CYAN + "--javaVersion=<java-version>" + RESET);
//		System.out.println(TAB + TAB + "Compiles a library for a specific Java version. For example");
//		System.out.println(TAB + TAB + "--javaVersion=1.8 to compile classes for Java 1.8 and higher or");
//		System.out.println(TAB + TAB + "--javaVersion=9 to compile classes for Java 9 and higher. By default, we");
//		System.out.println(TAB + TAB + "use Java version 1.8!");
//		System.out.println();
		System.out.println(TAB + CYAN + "--verbose" + RESET);
		System.out.println(TAB + TAB + "Outputs message about what the compiler is doing. There's a lot of");
		System.out.println(TAB + TAB + "messages output by the compiler, you've been warned!");
		System.out.println();
	}
	
	private static void printNoInputFiles() {
		System.out.println("You didn't supply any files for me to compile! I need files like:");
		System.out.println();
		System.out.println(TAB + GREEN + "java -jar flpc.jar MyLibrary.flp" + RESET);
		System.out.println(TAB + GREEN + "java -jar flpc.jar MyLibrary.flp SomeOtherLibrary.flp" + RESET);
		System.out.println();
	}
	
	private static void printBadFlag(String flag, String type) {
		System.out.println("This flag you gave me looks weird:");
		System.out.println();
		System.out.println(TAB + RED + flag + RESET);
		System.out.println();
		System.out.println("I need a valid " + YELLOW + type + RESET + " value. For example:");
		System.out.println();
	}
	
	private static void printBadArgument(String argument, String type) {
		System.out.println("This argument is giving me issues:");
		System.out.println();
		System.out.println(TAB + RED + argument + RESET);
		System.out.println();
		System.out.println("I need a valid " + YELLOW + type + RESET + " value. For example:");
		System.out.println();
	}
	
	private static void cantFindFile(String file) {
		System.out.println("I can't find this file:");
		System.out.println();
		System.out.println(TAB + RED + file + RESET);
		System.out.println();

		File f = new File(file).getAbsoluteFile();		
		List<Pair<String, Integer>> lev = new ArrayList<>();
		File[] files;
		if(new File(file).isDirectory()) {
			files = f.listFiles();
		} else {
			files = f.getParentFile().listFiles();
		}
		for(File similar : files) {
			lev.add(Pair.of(similar.getName(), Levenshtein.distance(similar.getName(), file)));
		}
		List<String> potential = lev.stream()
				.sorted(Comparator.comparing(Pair::second))
				.map(Pair::first)
				.filter(s -> s.endsWith(".flp"))
				.collect(Collectors.toList());
		System.out.println("Did you mean: ");
		System.out.println();
		for(int i = 0; i < 3; i++) {
			try {
				System.out.println(TAB + GREEN + potential.get(i));
			} catch(IndexOutOfBoundsException e) {
			}
		}
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
			
			if(s.equals("--help")) {
				printHelp();
				return;
			}
			
			if(s.startsWith("--javaversion")) {
				
			}
			
			if(s.startsWith("--outputdir")) {
				if(s.substring(12).length() == 0) {
					printBadFlag(str, "<output-directory>");
					System.out.println(TAB + GREEN + "--outputDir=./build");
					System.out.println();
					return;
				}
				outputDir = new File(str.substring(12));
			}
			
			if(s.equals("--verbose")) {
				FLPCompiler.VERBOSE = true;
			} else {
				FLPCompiler.VERBOSE = false;
			}
		}
		
		if(Arrays.stream(args).filter(s -> !s.startsWith("--")).count() == 0) {
			printNoInputFiles();
			return;
		}
		
		for(String str : args) {
			if(!str.startsWith("--")) {
				if(!str.endsWith(".flp")) {
					printBadArgument(str, "<flp-file>");
					System.out.println(TAB + GREEN + "MyLibrary.flp");
					System.out.println();
					return;
				}
				File fileToCompile = new File(str);
				if(!fileToCompile.exists()) {
					cantFindFile(str);
					return;
				}
				new FLPCompiler(new File(str), outputDir).compile();
				System.out.println("Compiled " + str);
			}
		}
	}
}
