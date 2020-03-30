import java.io.File;

import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.parser.util.Pair;

public class Testing {
	
	public static void main(String[] args) throws Exception {
		
		new FLPCompiler(new File("test.flp"), new File("classfolder")).compile();
		
		System.out.println("Sample.id(true) = " + Sample.id(true));
		System.out.println("Sample.id(\"hello\") = " + Sample.id("hello"));
		System.out.println("Sample.id(Pair.of(\"hello\", 4)) = " + Sample.id(Pair.of("hello", 4)));
		
		System.out.println(Sample.red());
		System.out.println(Sample.yellow());
		
		System.out.println(Sample.someOtherBool());
		
//		System.out.println();
//		Sample.Color color = Sample.red();
//		System.out.println("red: " + color.red);
//		System.out.println("green: " + color.green);
//		System.out.println("blue: " + color.blue);
//		
//		color = Sample.green();
//		System.out.println("red: " + color.red);
//		System.out.println("green: " + color.green);
//		System.out.println("blue: " + color.blue);
	}
	
}
