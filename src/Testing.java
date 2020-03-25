import java.io.File;
import java.io.FileInputStream;

import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;

public class Testing {

	public static void main(String[] args) throws Exception {		
		
		File file = new File("test.flp");
		FLPLibrary lib = new ForteLangPrimeParser(new FileInputStream(file)).input();
//		System.out.println(lib);
		
		FLPTestLibraryWithMethods.compileAndWrite(lib);
		
//		Files.write(new File("classfolder", "Sample.class").toPath(), FLPTestLibraryWithMethods.dump(
//				"FLPTestLibrary2.fl", 
//				"Sample", null, 
//				new FLPFunction[] {
//						new FLPFunction("returnTwenty", new Type[0], new IntType(), new ExprIntLit(20))
//				}));
		
		System.out.println(Sample.justTwo());

		
//		FLPTestLibrary1.returnOne();
//		new FLPTestLibrary1();
		
//		System.out.println(FLPTestLibrary1.);
//		System.out.println("A");
//		
//		String[] arr = new String[] {};
//		System.out.println(arr);
//		
//		
//		System.out.println(Single3.returnOne());
////		
////		Files.write(new File("classfolder", "Single3.class").toPath(), Single3Dump.dump());
//		Files.write(new File("classfolder", "FLPTestLibrary1.class").toPath(), FLPTestLibrary.dump());
		
	}
	
	// 0 = FALSE. 1 = TRUE
//	static boolean b() {
//		return false;
//	}
	
	
}
