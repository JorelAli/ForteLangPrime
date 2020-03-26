package dev.jorel.fortelangprime.parser.util;

import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtils {

	/**
	 * Maps conditionally. For example
	 * <code>.stream().map(conditioning(Type::isGeneric, Type::toGenericBytecodeString, Type::toBytecodeString));</code>
	 * @param <T>
	 * @param <R>
	 * @param p The predicate to satisfy
	 * @param ifTrue The mapping function to return if true
	 * @param ifFalse The mapping function to return if false
	 * @return
	 */
	public static <T, R> Function<T, R> conditioning(Predicate<T> p, Function<T, R> ifTrue, Function<T, R> ifFalse) {
		return input -> p.test(input) ? ifTrue.apply(input) : ifFalse.apply(input);
	}
	
}
