import java.io.File;

import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.parser.util.Pair;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		File[] files = new File(".").listFiles(f -> f.toString().endsWith("flp"));
		for(File file : files) {
			new FLPCompiler(file, new File("classfolder")).compile();
		}
		
		System.out.println("Sample.id(true) = " + Sample.id(true));
		System.out.println("Sample.id(\"hello\") = " + Sample.id("hello"));
		System.out.println("Sample.id(Pair.of(\"hello\", 4)) = " + Sample.id(Pair.of("hello", 4)));
		
		System.out.println(Sample.red());
		System.out.println(Sample.yellow());
		
		System.out.println(Sample.someOtherBool());
		System.out.println(Sample.someOtherOtherBool());
		
	}
	
}
