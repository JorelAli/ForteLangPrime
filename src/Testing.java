import java.io.File;
import java.lang.reflect.Field;

import dev.jorel.fortelangprime.BytecodeGenerator;
import dev.jorel.fortelangprime.BytecodeGenerator.JavaVersion;
import dev.jorel.fortelangprime.ast.FLPLibrary;
import dev.jorel.fortelangprime.parser.ForteLangPrimeParser;
import dev.jorel.fortelangprime.parser.util.Pair;

public class Testing {
	
	public static void main(String[] args) throws Exception {		
		
		FLPLibrary lib = ForteLangPrimeParser.parse(new File("test.flp"));
		BytecodeGenerator generator = new BytecodeGenerator(ForteLangPrimeParser.getTypingContext(), lib, JavaVersion.V_8);
		generator.compile();
		generator.writeToFile(new File("classfolder"));
		
//		System.out.println(Sample.a);
		
//		System.out.println(Sample.gen().a);
//		System.out.println(Sample.a);
		
		System.out.println(Sample.id(true));
		System.out.println(Sample.id("hello"));
		System.out.println(Sample.id(Pair.of("hello", 4)));
//		Sample.Sample$Color
//		Class s = Color.class;
//		new Sample.Color();
//		new Sample.Color(2);
		
		
//		for(Field f : Sample.mk().getClass().getDeclaredFields()) {
//			System.out.println(f.getName());
//		}
		System.out.println(Sample.mk().red);
	}
	
	public void a() {
		int i = 0;
		
		System.out.println(i);
		System.out.println("hi" + i);
	}
	
	public boolean not(boolean input) {
		return input ? false : true;
	}
	
	public static boolean not2(boolean input, boolean input2) {
		return input && input2;
	}
	
	public static <T,E> T generic(T a, E r) {
	    return a;
	}
	
	public static int i(int i) {
		switch(i) {
			case -1:
				return 1;
			case 5:
				return 2;
			default:
				return 3;
		}
	}
	
//	FieldVisitor fieldVisitor;// = classWriter.visitField(ACC_PRIVATE | ACC_FINAL | ACC_STATIC | ACC_SYNTHETIC, "ENUM$VALUES", "[LColor;", null, null);
//	fieldVisitor = classWriter.visitField(ACC_PUBLIC | ACC_FINAL | ACC_STATIC | ACC_SYNTHETIC, "f", "Ljava/lang/String;", null, "hello!");
//	fieldVisitor.visitEnd();
	
	public static int s(String i) {
		switch(i) {
			case "hi":
				return 1;
			case "ho":
				return 2;
			default:
				return 3;
		}
	}
	
}
