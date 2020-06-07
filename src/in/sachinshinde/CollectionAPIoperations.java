package in.sachinshinde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import in.sachinshinde.model.Person;

public class CollectionAPIoperations {

	public static void main(String[] args) {
		forEach_removeIf_replaceAll_sort_Methods();
		
		comparatorMethods();
		
		//intStreamOperations();
		
	}

	private static void forEach_removeIf_replaceAll_sort_Methods() {
		
		List<String> strings = Arrays.asList("one","two","three");
		
		// Use of the forEach method call on the collection 
		strings.forEach(System.out::println);
		
		// Copy the list into the Collection, so that it can allow you to modify the elements from the collection 
		List<String> list = new ArrayList<String>(strings);
		
		// use of the removeIf() method to modify the collection if the predicate condition is true.
		list.removeIf(s-> s.startsWith("t"));
		
		System.out.println("\n Modified list is: \n");
		list.forEach(System.out::println);
		
		System.out.println("\n Modified list is: uppercase \n");
		List<String> list2 = new ArrayList<String>(strings);
		// Using the replaceAll method 
		list2.replaceAll(String::toUpperCase);
		list2.forEach(System.out::println);
		
		System.out.println("\n Sorted list is: \n");
//		list2.sort((t1,t2)->t1.compareTo(t2));
		list2.sort(Comparator.naturalOrder());
		list2.forEach(System.out::println);
	}


	private static void comparatorMethods() {
		
		Person p1 = new Person("Sachin", "Shinde", 30);
		Person p2 = new Person("Rahul", "Jha", 31);
		Person p3 = new Person("Pravin", "Kelkar", 45);
		Person p4 = new Person("Amit", "Lohade", 35);
		Person p5 = new Person("Saurabh", "Shinde", 30);
		
		List<Person> persons = new LinkedList<Person>();
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);
		persons.add(p4);
		persons.add(p5);

		
		System.out.println("---------------------------------------------------");
		
		// Sort the people based on last name, display the result with first name 
		// Use of comparing()
		persons.stream()
				.sorted(Comparator.comparing(Person::getLastName))
				.forEach(p->System.out.println(p.getFirstName()));
		
		System.out.println("---------------------------------------------------");
		
		// Sort the people based on the age, display the result with first name 
		// Use of comparingInt()
		persons.stream()
				.sorted(Comparator.comparingInt(Person::getAge))
				.forEach(p->System.out.println(p.getFirstName()));
		
		System.out.println("---------------------------------------------------");
		
		// Sort first by "Last Name" and then by "First Name" & display the first name
		// Simple method is apply twice Sorted() and another method is use thenComparing() for already compared
		persons.stream()
				.sorted(
									Comparator.comparing(Person::getLastName)
											  .thenComparing(Person::getFirstName)
						)
				.forEach(p->System.out.println(p.getFirstName()));
		
		System.out.println("---------------------------------------------------");
		
		// How to reverse the comparison result
		// Use the reversed() method on the comparator 
		Comparator<Person> pComp =  Comparator.comparing(Person::getFirstName);
		persons.sort(pComp.reversed());
		persons.forEach(p->System.out.println(p.getFirstName()));
		
		System.out.println("---------------------------------------------------");
		
		// Compare using the natural order
		// Remember Comparator with naturalOrder accepts only native 
		Comparator<String> p2Comp = Comparator.naturalOrder();
		persons.stream()
				.map(Person::getFirstName)
				.sorted(p2Comp)
				.forEach(System.out::println);
				
		System.out.println("---------------------------------------------------");
		
		// Compare the values having null values, keep the null values at the last
		// use nullsLast() or nullsFirst() on the Comparator, if the stream contains the null values
		// you can skip the null using the filter(Objects::nonNull)
		persons.add(new Person(null, null,0));
		Comparator<String> pNullsComp = Comparator.nullsLast(Comparator.naturalOrder());
		persons.stream()
			.map(Person::getFirstName)
			.sorted(pNullsComp)
			.forEach(System.out::println);
		
		System.out.println("---------------------------------------------------");
		
	}

}
