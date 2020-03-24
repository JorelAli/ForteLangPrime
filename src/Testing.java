import java.io.File;
import java.nio.file.Files;

public class Testing {

	public static void main(String[] args) throws Exception {
		System.out.println("A");
		System.out.println(Single3.returnOne());
//		
		Files.write(new File("classfolder", "Single3.class").toPath(), Single3Dump.dump());
		
	}
	
	
}
