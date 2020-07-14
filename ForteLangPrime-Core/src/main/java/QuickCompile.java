import java.io.File;

import dev.jorel.fortelangprime.compiler.FLPCompiler;

public class QuickCompile {
	
	public static void main(String[] args) throws Exception {
		File[] files = new File(".").listFiles(f -> f.toString().endsWith("flp"));
		for(File file : files) {
			new FLPCompiler(file, new File("classfolder")).compile();
		}
	}
}
