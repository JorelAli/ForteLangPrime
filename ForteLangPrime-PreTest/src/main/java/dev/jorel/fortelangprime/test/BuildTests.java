package dev.jorel.fortelangprime.test;
import java.io.File;

import dev.jorel.fortelangprime.compiler.FLPCompiler;

public class BuildTests {
	
	public static void main(String[] args) throws Exception {
		File[] files = new File("./ForteLangPrime-PreTest/src/main/resources").listFiles(f -> f.toString().endsWith("flp"));
//		File file = new File("./src/main/resources/Tests.flp");
//		FLPCompiler.log(new File(".").getAbsolutePath());
//		new FLPCompiler(file, new File("./src/main/java")).compile();
//		System.out.println(files == null);
		for(File file : files) {
			new FLPCompiler(file, new File("./ForteLangPrime-PreTest/src/main/resources")).compile();
		}
	}
}
