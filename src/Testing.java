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
		System.out.println(Sample.panicTest());

	}
	
	static String str() {
		/*
		 * 
methodVisitor.visitTypeInsn(NEW, "java/lang/RuntimeException");
methodVisitor.visitInsn(DUP);
methodVisitor.visitLdcInsn("Undefined");
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V", false);
methodVisitor.visitInsn(ATHROW);
		 */
		throw new RuntimeException("Undefined");
	}
	
	
	
}
