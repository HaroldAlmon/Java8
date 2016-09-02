package com.translationdata;

import java.util.function.Function;

public class HighOrderFunction {

	public static void main(String[] args) {
		// This is a Lambda that takes a function as input and returns a function as output... 
		Function<Function<Integer, Integer>, Function<Integer, Integer>> twice = f -> f.andThen(f);
		Function<Function<Integer, Integer>, Function<Integer, Integer>> thrice = f -> f.andThen(f).andThen(f);
		System.out.println( twice.apply(x -> x + 3).apply(7) ); // 13
		System.out.println( thrice.apply(x -> x + 3).apply(7) ); // 16
	}
}
