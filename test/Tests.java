import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import dev.jorel.fortelangprime.compiler.CompilationException;
import dev.jorel.fortelangprime.compiler.FLPCompiler;
import dev.jorel.fortelangprime.parser.ParseException;
import dev.jorel.fortelangprime.parser.exceptions.TypeException;

public class Tests {
	
	static {
		FLPCompiler.VERBOSE = false;
		for(File file : new File(".").listFiles(f -> f.toString().endsWith("flp"))) {
			try {
				new FLPCompiler(file, new File("classfolder")).compile();
			} catch (ParseException | IOException | CompilationException | TypeException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testEquality() {
		assertTrue(Sample.eqInt(2, 2));
		assertFalse(Sample.eqInt(2, 3));
		
		assertTrue(Sample.eqStr("hello", "hello"));
		assertFalse(Sample.eqStr("hello", "world"));
		
		assertTrue(Sample.eqBool(true, true));
		assertTrue(Sample.eqBool(false, false));
		assertFalse(Sample.eqBool(true, false));
		assertFalse(Sample.eqBool(false, true));
		
		assertTrue(Sample.eqColor(Sample.red(), Sample.red()));
	}
	
	@Test
	public void testMath() {
		assertEquals(20, Sample.add(2, 18));
		assertEquals(20, Sample.sub(23, 3));
		assertEquals(20, Sample.mul(4, 5));
		assertEquals(16, Sample.pow(2, 4));
	}
	
}
