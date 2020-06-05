package in.sachinshinde;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class StringOperations {
	
	public static void main(String args[]) {
		operationOnString();
		
		IOoperations();
	}

	private static void operationOnString() {
		String message = "Hello Wolrd !";
		
		// **** Writing a string in UpperCase letter
		
		System.out.println(message.toUpperCase());
		
		message
			.chars()
			.mapToObj(ch->(char)ch)
			.map(Character::toUpperCase)
			.forEach(System.out::print);
			
		System.out.println();
		
		// **** StringJoiner to combine Strings
		
		StringJoiner sj = new StringJoiner(",", "{", "}");
		sj.add("Sachin");
		sj.add("Rahul");
		sj.add("Pratik");
		System.out.println();
		System.out.println("String concatenated: "+ sj.toString());
		
		Stream.of(sj.add("Vivek"))
			.forEach(System.out::println);
		
		
		// **** Join method on string
		
		String[] lang = {"java","angular","python"};
		System.out.println(String.join("|", lang));
		
	}
	
	private static void IOoperations() {
		System.out.println();

		// **** using operation "lines" on files 
		try{
			// ** Approach-1 
			BufferedReader reader = Files.newBufferedReader(Paths.get("resources/setupact.log"));
			reader.lines()
					.filter(line -> line.contains("error"))
					.findFirst()
					.ifPresent(System.out::println);
			
			// ** Approach-2
			Path p = Paths.get("resources","setupact.log");
			Files.lines(p)
					.filter(line -> line.contains("error"))
					.findFirst()
					.ifPresent(System.out::println);
			
			// **How to get the list of files or folders from a specific directory

			// Getting the files from the same directory only, not from the available folders from that directory 
			System.out.println("\n By LIST operation: \n");
			Files.list(Paths.get("resources"))
					.filter(apath -> apath.toFile().isFile())
					.forEach(System.out::println);
			
			// getting all files, including the files from the folders in that directory  
			System.out.println("\n By WALK operation: \n");
			Files.walk(Paths.get("resources"))
					.filter(Files::isRegularFile)
					.forEach(System.out::println);

			// Getting all JSON files from the directory  
			System.out.println("\n By WALK operation: (JSON files) \n");
			Files.walk(Paths.get("resources"))
					.filter(Files::isRegularFile)
					.filter(p1->p1.toString().endsWith("json"))
					.forEach(System.out::println);
					
			// Getting all TXT files from "TXT_Files" folder in the directory  
			System.out.println("\n By WALK operation: (txt files from the TXT_Files folder) \n");
			Files.walk(Paths.get("resources"))
					.filter(Files::isRegularFile)
					.filter(p2->p2.toString().contains("TXT_Files"))
					.filter(p3->p3.toString().endsWith("txt"))
					.forEach(System.out::println);
						
		}catch(Exception e) {
			System.out.println("Exception :" +e);
			
		}
	}

}
