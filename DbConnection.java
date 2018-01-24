package IOT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// source adaptation: https://www.mkyong.com/jdbc/how-to-connect-to-mysql-with-jdbc-driver-java/
// Use database root password
public class DbConnection {
	Connection connection = null;

	public void connect() {
		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/feedback", "root", "qwerty");

		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (this.connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
	}

	public void disconnect() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertRow(long dateInMs, String whats_exciting, String job_skills, String concepts_learned, String questions_asked, String challenges,
			String research, String improvements, String employer_discussions, String helped) {
		// the mysql insert statement
		String query = "INSERT INTO feedback.comments (session_date, whats_exciting, job_skills, concepts_learned, questions_asked, challenges,"+
				"research, improvements, employer_discussions, helped) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
	
		
		try {
			// create the mysql insert preparedstatement
			if(this.connection != null) {
				PreparedStatement preparedStmt = this.connection.prepareStatement(query);
				preparedStmt.setDate(1, new Date(dateInMs));
				preparedStmt.setString(2, whats_exciting);
				preparedStmt.setString(3, job_skills);
				preparedStmt.setString(4, concepts_learned);
				preparedStmt.setString(5, questions_asked);
				preparedStmt.setString(6, challenges);
				preparedStmt.setString(7, research);
				preparedStmt.setString(8, improvements);
				preparedStmt.setString(9, employer_discussions);
				preparedStmt.setString(10, helped);
				preparedStmt.execute();
			} else {
				System.out.println("Cannot create a connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteRowbyDate() {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter Date you want to delete ");
	    String input = scanner.next();
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		long milliseconds=0;
		try {
		    java.util.Date d = f.parse(input);
		    milliseconds = d.getTime();
		   
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
	    Date date= new Date(milliseconds);
		String query = "delete from feedback.comments where session_date=?";	
		try {
			// create the mysql insert preparedstatement
			if(this.connection != null) {
				PreparedStatement preparedStmt = this.connection.prepareStatement(query);
				preparedStmt.setDate(1, date);
				preparedStmt.execute();
			} else {
				System.out.println("Cannot create a connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void searchRowbyDate() {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter Date you want to Search ");
	    String input = scanner.next();
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		long milliseconds=0;
		try {
		    java.util.Date d = f.parse(input);
		    milliseconds = d.getTime();
		   
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		
	    Date date= new Date(milliseconds);
		String query = "select * from feedback.comments where session_date=?";	
		try {
			// create the mysql insert preparedstatement
			if(this.connection != null) {
				PreparedStatement preparedStmt = this.connection.prepareStatement(query);
				preparedStmt.setDate(1, date);
				ResultSet rs = preparedStmt.executeQuery();
				Records record=null;
				while ( rs.next() )
			    {
				  record = new Records();
				  record.setId  ( rs.getInt("id") );
				  record.setSession_date( rs.getDate("session_date") );
				  record.setWhats_eciting(rs.getString("whats_exciting"));
				  record.setJob_skills(rs.getString("job_skills"));
				  record.setConcepts_learned(rs.getString("concepts_learned"));
				  record.setQuestions_asked(rs.getString("questions_asked"));
				  record.setChallenges(rs.getString("challenges"));
				  record.setResearch(rs.getString("research"));
				  record.setImprovements(rs.getString("improvements"));
				  record.setEmployer_discussions(rs.getString("employer_discussions"));
				  record.setHelped(rs.getString("helped"));
			    }
				System.out.println("Record ID:"+record.getId());
				System.out.println("Record Date:"+record.getSession_date());
				System.out.println("Record Whats Exciting:"+record.getWhats_eciting());
				System.out.println("Record Concepts Learned:"+record.getConcepts_learned());
				System.out.println("Record Questions Asked:"+record.getQuestions_asked());
				System.out.println("Record Challenges:"+record.getChallenges());
				System.out.println("Record Research:"+record.getResearch());
				System.out.println("Record Improvements:"+record.getImprovements());
				System.out.println("Record Employer Discussions:"+record.getEmployer_discussions());
				System.out.println("Record Helped:"+record.getHelped());
			} else {
				System.out.println("Cannot create a connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
