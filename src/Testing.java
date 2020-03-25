import java.io.File;
import java.io.FileInputStream;

import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;

public class Testing {

	public static void main(String[] args) throws Exception {		
		
		File file = new File("test.flp");
		FLPLibrary lib = new ForteLangPrimeParser(new FileInputStream(file)).input();
		System.out.println(lib);
		
		FLPTestLibraryWithMethods.compileAndWrite(lib);
		
		System.out.println(Sample.justTwo());
		System.out.println(Sample.someBool());
		System.out.println(Sample.aString());

	}
	
	static String str() {
		return "hello".substring(1, "".length() - 1);
	}
	
	
	
}
