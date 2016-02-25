package com.translationdata.prototypes;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Test;

public class PredicatesTest {
	public void runPredicates() {
		List<Integer> numbers = Arrays.asList(-3, -2, -1, 0, 1,2,3);
		Predicate<Integer> isNegative = element -> element < 0;
		Predicate<Integer> isOdd = element -> element % 2 != 0;
		Predicate<Integer> predicates = isNegative.and(isOdd);
		
		for( Integer elem : numbers) {
			System.out.printf( "%d, %s\n", elem, predicates.test(elem) );
		}

		System.out.println("---- Using forEach ----");

		numbers.stream().filter(predicates).forEach(System.out::println);
	}
	
	@Test
	public void test() {
		runPredicates();
	}

}
