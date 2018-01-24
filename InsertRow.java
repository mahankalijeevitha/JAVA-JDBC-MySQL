package IOT;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class InsertRow {
      
	public static void insertRow() {
		DbConnection connection = new DbConnection();
		connection.connect();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Get Ready to insert data");
		System.out.println("Enter Date:");
		String input = scanner.next();
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		long milliseconds=0;
		try {
		    Date d = f.parse(input);
		    milliseconds = d.getTime();
		   
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
		System.out.println("whats exciting:");
		String whats_exciting = scanner.next();
		System.out.println("Job Skills:");
		String job_skills = scanner.next();
		System.out.println("Concepts Learned:");
		String concepts_learned = scanner.next();
		System.out.println("Questions Asked:");
		String questions_asked = scanner.next();
		System.out.println("Challenges:");
		String challenges = scanner.next();
		System.out.println("Research:");
		String research = scanner.next();
		System.out.println("Improvements:");
		String improvements = scanner.next();
		System.out.println("Employer Discussions:");
		String employer_discussions = scanner.next();
		System.out.println("Help I Offered:");
		String helped = scanner.next();
		
		
		connection.insertRow(milliseconds,whats_exciting,job_skills,concepts_learned,questions_asked,challenges,research,improvements,employer_discussions,helped);
		connection.disconnect();
	}
}
