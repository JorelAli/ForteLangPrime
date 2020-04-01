import java.io.File;

import dev.jorel.fortelangprime.compiler.FLPCompiler;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		File[] files = new File(".").listFiles(f -> f.toString().endsWith("flp"));
		for(File file : files) {
			new FLPCompiler(file, new File("classfolder")).compile();
		}
		
//		System.out.println("Sample.id(true) = " + Sample.id(true));
//		System.out.println("Sample.id(\"hello\") = " + Sample.id("hello"));
//		System.out.println("Sample.id(Pair.of(\"hello\", 4)) = " + Sample.id(Pair.of("hello", 4)));
//		
//		System.out.println(Sample.red());
//		System.out.println(Sample.yellow());
//		
//		System.out.println(Sample.someOtherBool());
//		System.out.println(Sample.someOtherOtherBool());
//		System.out.println(Sample.and(true, false));
		
//		System.out.println(Sample.equality(2, 2));
//		System.out.println(Sample.equality(2, 1));
		
		System.out.println(Sample.eqInt(2, 2));
		System.out.println(Sample.eqInt(2, 3));
		
		System.out.println(Sample.eqStr("hello", "hello"));
		System.out.println(Sample.eqStr("hello", "world"));
		
		System.out.println(Sample.eqBool(true, true));
		System.out.println(Sample.eqBool(true, false));
		
		System.out.println(Sample.eqColor(Sample.red(), Sample.red()));
		System.out.println(Sample.testCustomOperator());
	}
	
	
	
}
