import java.io.File;

import dev.jorel.fortelangprime.BytecodeGenerator;
import dev.jorel.fortelangprime.BytecodeGenerator.JavaVersion;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;

public class Testing {

	public static void main(String[] args) throws Exception {		
		
		FLPLibrary lib = ForteLangPrimeParser.parse(new File("test.flp"));
		BytecodeGenerator generator = new BytecodeGenerator(ForteLangPrimeParser.getTypingContext(), lib, JavaVersion.V_8);
		generator.compile();
		generator.writeToFile(new File("classfolder"));
		
		System.out.println(Sample.identity1(true));
		System.out.println(Sample.not(true));
		System.out.println(Sample.not(false));
		System.out.println(Sample.alwaysFalse());
		System.out.println(Sample.generic(true));
		
		
//		Sample.panicTest();
//		Sample.
//		Sample.panicTest'();
//		System.out.println(Sample.justTwo'());
//		System.out.println(Sample.someBool());
//		System.out.println(Sample.aString());
//		System.out.println(Sample.panicTest());

	}
	
	static <ype, K> ype identity(ype a, K t) {
//		.Testing. "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<T:Ljava/lang/Object;K:Ljava/lang/Object;>(TT;TK;)TT;",
		return a;
	}
	
}
