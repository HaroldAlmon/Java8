package com.translationdata;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/*abstract class Mammal {
	protected String type;

	public String getType() {
		return type;
	}
	
	static void print(List<? extends Mammal> mammalList) {
		mammelList.add(new Zebra());	// compiler error because mammalList is covariant
		mammelList.add(new Lion());		// compiler error because mammalList is covariant
		mammelList.add(new Mammal());	// compiler errors because Mammal class is abstract and mammalList is covariant 
		for(Mammal mammal : mammalList)
			System.out.println(mammal.getType());
	}
}*/
class Zebra extends Mammal {
	Zebra() { type = "Zebra"; }
}
class Lion extends Mammal {
	Lion() { type = "Lion"; }
}

public class Covariance {
	List<? extends Number> classNumbers = new ArrayList<Number>();
	
	Covariance() {
		classNumbers.add(new Integer(1)); // Compiler error because.
	}
	
	void populateCage() {
		List<Mammal> cage = new ArrayList<Mammal>();
		cage.add( new Zebra() );
		cage.add(new Lion());
		
		List<Zebra> zebraCage = new ArrayList<Zebra>();
		cage.add( new Zebra() );
		cage.add( new Zebra() );
		cage.add( new Zebra() );
		cage.add( new Zebra() );
		
		Mammal.print(cage);
		Mammal.print(zebraCage);
	}
	
	public void addToCoviantList() {
		@SuppressWarnings("serial")
		List<? extends Number> myNums = new ArrayList<Number>() {
			{
				add(new Integer(1));
				add(new Long(2));
				add(new Short((short) 3));
			}
		};

		List<? extends Number> myNums2;
		
		// The adds work because the generic parameter is invariant. The collection datatype must be an exact match 
		// but the collection can contain a Number or a subtype of a Number.
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
		// myNums2 has a covariant generic parameter so the assignment works (i.e. Integer extends Number).
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
		
		// But adding an Integer to intList is okay!
		intList.add(new Integer(5));
		
		// The following demonstrates why the write to mynum2, a list of Integers, is disallowed. 
		// You cannot add a Long to a list of Integers.
		myNums2.add(new Long(5)); // Compiler error
		
		intList.add(new Integer(4));
		myNums2 = intList;

		// Try to subvert the type system!
		List<Number> myNumbers;
		
		// Java compiler catches the type mismatch here...
		myNumbers = myNums3; 		// Compiler error, cannot assign a coviant list to an invariant list.
		myNumbers.add(new Long(4)); // If this executes (it will NOT execute becuase of the previous error), 
									// the Integer list is corrupted by the Long value.

		for (Number number : myNums) {
			System.out.println(number);
		}
		
		populateCage();
	}

	
	public void collectionsThatAreInvariant() {
		// Without changing the String generic type to Object on the left, fix he following compiler error...
		ArrayList<String> strings = new ArrayList<Object>();
		
		// Without change the String generic type to Object on the left, fix he following compiler error...
		ArrayList<Object> objects = new ArrayList<String>();
		
		//none of the following expressions will compile because the method function is invariant:
		Integer[] result = method(new ArrayList<Integer>());
		Number[] result2 = method(new ArrayList<Integer>());
		Object[] result3 = method(new ArrayList<Object>());
		
		Number[] result4 = method(new ArrayList<Number>());
		Object[] result5 = method(new ArrayList<Number>());
	}
	
	Number[] method(ArrayList<Number> list) {
		return null; 
	}
	
	@Test
	public void reification() {
		addToCoviantList();
	}

}
