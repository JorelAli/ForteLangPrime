package dev.jorel.fortelangprime.parser.util;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamUtils {

	/**
	 * Maps conditionally. For example
	 * <code>.stream().map(conditioning(Type::isGeneric, Type::toGenericBytecodeString, StreamUtils.with(Type::toBytecodeString, context)));</code>
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
	
	/**
	 * Partially applies a parameter to a function
	 * @param <T>
	 * @param <R>
	 * @param <U>
	 * @param f
	 * @param p
	 * @return
	 */
	public static <T, R, U> Function<T, U> with(BiFunction<T, R, U> f, R p) {
		return input -> f.apply(input, p);
	}
	
	
}
