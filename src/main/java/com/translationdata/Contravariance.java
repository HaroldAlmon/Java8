package com.translationdata;

import java.util.ArrayList;
import java.util.List;

public class Contravariance {
	public static void main(String[] args) {
		App app = new App();
		app.run();
	}
}

class App {
	public void run() {
		List<Animal> animals = new ArrayList<>();
		List<Mammal> mammals = new ArrayList<>();
		List<Human> humans = new ArrayList<>();

		animals.add(new Animal());
		animals.add(new Animal());
		animals.add(new Animal());
		
		mammals.add(new Mammal());
		mammals.add(new Mammal());
		mammals.add(new Mammal());
		
		humans.add(new Human());
		humans.add(new Human());
		humans.add(new Human());
		
		print("Before copy()", animals);
		
		Collections.copy(animals, humans);
		print("After copy()", animals);
		
		Collections.append(animals, mammals);
		print("After append()", animals);
		
		Animal animal = animals.get(4);
	}
	
		
	public void print(String context,List<? extends Animal> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(context + ": I am a " + list.get(i));
		}
		System.out.println();
	}
}
	
class Animal {
	String type = "animal";
	
	public String toString() {
		return type;
	};
}

class Mammal extends Animal {
	Mammal() {
		type = "mammal";
	}
}

class Human extends Mammal {
	Human() {
		type = "human";
	}
}

class Collections {
	public static <T> void copy(List<? super T> dest , List<? extends T> src ) {
		for(int i=0; i < src.size(); i++) {
			dest.set(i, src.get(i)); 
		}
		
		// src.add(new Animal());  // ERROR, insertion not allowed into a covariant type
	}
	
	public static <T> void append(List<? super T> dest , List<? extends T> src ) {
		for(int i=0; i < src.size(); i++) {
			dest.add(src.get(i)); 
		}
		
		//Animal animal = dest.get(4); // ERROR, reading a contravariant type is not allowed 
		Object animal = dest.get(4);   // except into an Object like this
	}
}
