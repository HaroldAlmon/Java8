package com.translationdata;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class StreamTest {
	public static void main(String[] args) {
		System.out.println("Hello Harold");
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,2,3,4);
		numbers.stream()
			   .filter(e -> e % 2 == 0)
			   .forEach(System.out::println);
		
		List<Integer> evenNumbers = numbers.stream()
			   .filter(e -> e % 2 == 0)
			   .map( e -> e + 100)
			   .collect( toList() );
		
		evenNumbers.forEach(System.out::println);
		
		Set<Integer> evenSet = numbers.stream()
		   .filter(e -> e % 2 == 0)
		   .collect(toSet());
		
		evenSet.forEach(System.out::println);
	}
}
