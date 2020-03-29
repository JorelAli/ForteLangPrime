import java.io.File;

import dev.jorel.fortelangprime.BytecodeGenerator;
import dev.jorel.fortelangprime.BytecodeGenerator.JavaVersion;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;
import dev.jorel.fortelangprime.parser.util.Pair;

public class Testing {
	
	public static void main(String[] args) throws Exception {
		
		FLPLibrary lib = ForteLangPrimeParser.parse(new File("test.flp"));
		BytecodeGenerator generator = new BytecodeGenerator(ForteLangPrimeParser.getTypingContext(), lib, JavaVersion.V_8);
		generator.compile();
		generator.writeToFile(new File("classfolder"));
		
		System.out.println("Sample.id(true) = " + Sample.id(true));
		System.out.println("Sample.id(\"hello\") = " + Sample.id("hello"));
		System.out.println("Sample.id(Pair.of(\"hello\", 4)) = " + Sample.id(Pair.of("hello", 4)));
		
		System.out.println();
		Sample.Color color = Sample.red();
		System.out.println("red: " + color.red);
		System.out.println("green: " + color.green);
		System.out.println("blue: " + color.blue);
	}
	
}
