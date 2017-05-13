package com.translationdata;

import java.util.function.Predicate;

public class LambdaExpressions {
	public static void main(String[] args) {
		Predicate<Integer> p1 = e -> e < 20;
		Pig talk = e -> "I'm a pig and I say " + e;
	}

}

@FunctionalInterface
interface Pig<String> {
	public String talk(String message);
}
