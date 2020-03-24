package dev.jorel.fortelangprime;
import java.io.File;
import java.io.FileInputStream;

import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;
import dev.jorel.fortelangprime.parser.ParseException;

public class Main {
	public static void main(String[] args) throws ParseException, Exception {
		System.out.println("hi");
		
		
		
		File file = new File("test.flp");
		new ForteLangPrimeParser(new FileInputStream(file)).main();
		
//		String program = new String(Files.readAllBytes(file.toPath()));
//		ForteLangPrime.parse(program);
		
		
	}
}
