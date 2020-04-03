import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import dev.jorel.fortelangprime.util.Pair;

public class A {
	
	public static void main(String[] args) throws Exception {
		String[] ss = new String[] {"AbstractMethodError", 
				"Appendable", 
				"ArithmeticException", 
				"ArrayIndexOutOfBoundsException", 
				"ArrayStoreException", 
				"AssertionError", 
				"AutoCloseable", 
				"Boolean", 
				"BootstrapMethodError", 
				"Byte", 
				"CharSequence", 
				"Character", 
				"Class", 
				"ClassCastException", 
				"ClassCircularityError", 
				"ClassFormatError", 
				"ClassLoader", 
				"ClassNotFoundException", 
				"ClassValue", 
				"CloneNotSupportedException", 
				"Cloneable", 
				"Comparable", 
				"Compiler", 
				"Deprecated", 
				"Double", 
				"Enum", 
				"EnumConstantNotPresentException", 
				"Error", 
				"Exception", 
				"ExceptionInInitializerError", 
				"Float", 
				"FunctionalInterface", 
				"IllegalAccessError", 
				"IllegalAccessException", 
				"IllegalArgumentException", 
				"IllegalCallerException", 
				"IllegalMonitorStateException", 
				"IllegalStateException", 
				"IllegalThreadStateException", 
				"IncompatibleClassChangeError", 
				"IndexOutOfBoundsException", 
				"InheritableThreadLocal", 
				"InstantiationError", 
				"InstantiationException", 
				"Integer", 
				"InternalError", 
				"InterruptedException", 
				"Iterable", 
				"LayerInstantiationException", 
				"LinkageError", 
				"Long", 
				"Math", 
				"Module", 
				"ModuleLayer", 
				"NegativeArraySizeException", 
				"NoClassDefFoundError", 
				"NoSuchFieldError", 
				"NoSuchFieldException", 
				"NoSuchMethodError", 
				"NoSuchMethodException", 
				"NullPointerException", 
				"Number", 
				"NumberFormatException", 
				"Object", 
				"OutOfMemoryError", 
				"Override", 
				"Package", 
				"Process", 
				"ProcessBuilder", 
				"ProcessHandle", 
				"Readable", 
				"ReflectiveOperationException", 
				"Runnable", 
				"Runtime", 
				"RuntimeException", 
				"RuntimePermission", 
				"SafeVarargs", 
				"SecurityException", 
				"SecurityManager", 
				"Short", 
				"StackOverflowError", 
				"StackTraceElement", 
				"StackWalker", 
				"StrictMath", 
				"String", 
				"StringBuffer", 
				"StringBuilder", 
				"StringIndexOutOfBoundsException", 
				"SuppressWarnings", 
				"System", 
				"Thread", 
				"ThreadDeath", 
				"ThreadGroup", 
				"ThreadLocal", 
				"Throwable", 
				"TypeNotPresentException", 
				"UnknownError", 
				"UnsatisfiedLinkError", 
				"UnsupportedClassVersionError", 
				"UnsupportedOperationException", 
				"VerifyError", 
				"VirtualMachineError", 
				"Void"};
		
		List<Pair<String, Integer>> is = new ArrayList<>();
		List<Pair<String, Integer>> fs = new ArrayList<>();
		List<Pair<String, Integer>> ls = new ArrayList<>();
		List<Pair<String, Integer>> ds = new ArrayList<>();
		
		for(String s : ss) {
			Method[] ms = Class.forName("java.lang." + s).getDeclaredMethods();
			int i,d,f,l = i = d = f = l = 0;
			for(Method m : ms) {
				for(Parameter p : m.getParameters()) {
					if(p.getType().equals(int.class)) i++;
					if(p.getType().equals(float.class)) f++;
					if(p.getType().equals(long.class)) l++;
					if(p.getType().equals(double.class)) d++;
				}
				System.out.println(m.getName() + ": " + Arrays.toString(m.getParameters()));
			}
			if(i != 0) is.add(Pair.of(s, i));
			if(f != 0) fs.add(Pair.of(s, f));
			if(l != 0) ls.add(Pair.of(s, l));
			if(d != 0) ds.add(Pair.of(s, d));
		}
		System.out.println("Integers: " + is);
		System.out.println("Floats: " + fs);
		System.out.println("Longs: " + ls);
		System.out.println("Doubles: " + ds);
	}

	public double doubleAdd(double d, double d2) {
		return d + d2;
	}
	
	public boolean doubleEq(double d, double d2) {
		return d == d2;
	}
	
}
