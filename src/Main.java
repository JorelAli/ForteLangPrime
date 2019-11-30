import java.io.File;
import java.io.FileInputStream;

public class Main {
	public static void main(String[] args) throws ParseException, Exception {
		System.out.println("hi");
		
		
		
		File file = new File("test.flp");
		new ForteLangPrime(new FileInputStream(file)).main();
		
//		String program = new String(Files.readAllBytes(file.toPath()));
//		ForteLangPrime.parse(program);
	}
}
