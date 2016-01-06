package com.translationdata;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
enum ListType {
	INT, LONG
}
public class CovariantNumbers {
	List<? extends Number> getList(ListType listType) {
		List<? extends Number> result = null;
		@SuppressWarnings("serial")
		List<Integer> intList = new ArrayList<Integer>() {
			{
				add(new Integer(1));
				add(new Integer(2));
				add(new Integer(3));
			}
		};
		@SuppressWarnings("serial")
		List<Long> longList = new ArrayList<Long>() {
			{
				add(new Long(1));
				add(new Long(2));
				add(new Long(3));
			}
		};
		switch(listType) {
		case INT:
			result = intList;
			break;
			
		case LONG:
			result = longList;
			break;
		}
		return result;
	}
	
	public void addToCoviantList() {
		@SuppressWarnings("serial")
		List<? extends Number> polymorphicList2 = new ArrayList<Number>() {
			{
				add(new Integer(1));
				add(new Long(2));
				add(new Short((short) 3));
			}
		};
		
		// The code above is equivalent to the code below.
		List<? extends Number> polymorphicList; 
		ArrayList<Number> polymorphicListInit = new ArrayList<Number>() {
			{
				add(new Integer(1));
				add(new Long(2));
				add(new Short((short) 3));
			}
		};
		polymorphicList = polymorphicListInit;
		
		
		polymorphicList.add(new Integer(1));

		List<? extends Number> myNums2;
		
		// The adds work because the generic parameter is invariant. The collection datatype must be an exact match 
		// but the collection can contain a Number or a subtype of a Number.
		@SuppressWarnings("serial")
		List<Number> polyList = new ArrayList<Number>() {
			{
				add(new Integer(1));
				add(new Long(2));
				add(new Short((short) 3));
			}
		};
		
		// myNums2 has a coviant generic parameter so the assignment works (i.e. Number extends Number).
		myNums2 = polyList;
		
		List<? extends Number> myNums3;

		@SuppressWarnings("serial")
		List<Integer> intList = new ArrayList<Integer>() {
			{
				add(new Integer(1));
				add(new Integer(2));
				add(new Integer(3));
			}
		};
		
		// This looks like it should work but the compiler does not know if the collection is a list of Number, Integer, 
		// Long, Short, Byte, Double, AtomicInteger, or AtomicLong.
		myNums2.add(new Integer(4)); // Compiler error
		
		// But adding an Integer to intList is okay because intList is invariant!
		intList.add(new Integer(5));
		
		// The following demonstrates why the write to mynum2, a list of Integers, is disallowed. 
		// You cannot add a Long to a list of Integers.
		myNums2.add(new Long(5)); // Compiler error

		// Try to subvert the type system!
		List<Number> myNumbers;
		
		// Java compiler catches the type mismatch here...
		myNumbers = myNums3; 		// Compiler error
		myNumbers.add(new Long(4)); // If this executes (it will NOT execute because of the previous error), 
									// the Integer list is corrupted by the Long value.
		
		
		List<? extends Number> numberList;
		List<Integer> integerList = new ArrayList<Integer>() {
			{
				add(new Integer(1));
				add(new Integer(2));
				add(new Integer(3));
			}
		};
		numberList = integerList;
		numberList.set(0, 1);
		numberList.remove(0);
		
		// This looks like it should work but the compiler does not know if the collection is a list of Number, Integer, 
		// Long, Short, Byte, Double, AtomicInteger, or AtomicLong.
		numberList.add(new Integer(4)); // Compiler error
		
		// But adding an Integer to integerList is okay because integerList is invariant!
		integerList.add(new Integer(5));		
		
		// The following demonstrates why the write to integerList is disallowed. 
		// You cannot add a Long to a list of Integers.
		numberList.add(new Long(5)); // Compiler error
		
		for (Number number : polymorphicList) {
			System.out.println(number);
		}
	}

	
	
	@Test
	public void CovariantNumbers() {
		addToCoviantList();
	}

}
