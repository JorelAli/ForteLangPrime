import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import dev.jorel.fortelangprime.compiler.CompilationException;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class PreTests {

	public static void main(String[] args) throws FileNotFoundException, IOException, CompilationException, TypeException {
		File[] files = new File(".").listFiles(f -> f.toString().endsWith("flp"));
		for(File file : files) {
			new FLPCompiler(file, new File("test/testclassfolder")).compile();
		}
	}
	
}
