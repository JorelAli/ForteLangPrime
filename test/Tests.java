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
//		FLPCompiler.VERBOSE = false;
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
	public void testNegEquality() {
		assertFalse(Sample.neqInt(2, 2));
		assertTrue(Sample.neqInt(2, 3));
		
		assertFalse(Sample.neqStr("hello", "hello"));
		assertTrue(Sample.neqStr("hello", "world"));
		
		assertFalse(Sample.neqBool(true, true));
		assertFalse(Sample.neqBool(false, false));
		assertTrue(Sample.neqBool(true, false));
		assertTrue(Sample.neqBool(false, true));
		
		assertFalse(Sample.neqColor(Sample.red(), Sample.red()));
	}
	
	@Test
	public void testMath() {
		assertEquals(20, Sample.add(2, 18));
		assertEquals(20, Sample.sub(23, 3));
		assertEquals(20, Sample.mul(4, 5));
		assertEquals(16, Sample.pow(2, 4));
	}
	
	@Test
	public void testComparisons() {
		assertTrue(Sample.gt(20, 10));
		
		assertTrue(Sample.ge(20, 10));
		assertTrue(Sample.ge(20, 20));
		
		assertTrue(Sample.lt(5, 10));
		
		assertTrue(Sample.le(5, 10));
		assertTrue(Sample.le(5, 5));
	}
	
	@Test
	public void testDoubles() {
		assertEquals(123.2D, Sample.doubleTest(), 0);
		assertEquals(20.2D, Sample.doubleAdd(19.1D, 1.1D), 0.001D);
		assertEquals(15.3D, Sample.doubleSub(16.1D, 0.8D), 0.001D);
		assertEquals(19.1D * 112.1D, Sample.doubleMul(19.1D, 112.1D), 0.001D);
		assertEquals(19.1 / 1.2, Sample.doubleDiv(19.1D, 1.2D), 0.001D);
		assertEquals(19.1 % 1.2, Sample.doubleMod(19.1D, 1.2D), 0.001D);
	}
	
	@Test
	public void testPipes() {
		assertFalse(Sample.pipeline());
		assertTrue(Sample.secondPipeline());
	}
	
}
