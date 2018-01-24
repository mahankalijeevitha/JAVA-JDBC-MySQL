package IOT;

import java.util.Scanner;


public class TestMyFeedback {

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int optionNumber = 0;
		
		while(optionNumber != 5) {
			System.out.println("Workbook Options - Enter an option number");
			System.out.println("1: Populate table");
			System.out.println("2: Search Row by Date");
			System.out.println("3: Insert row into table");
			System.out.println("4: Delete row by Date");
			System.out.println("5: exit");
			
			optionNumber = reader.nextInt(); // Scans the next token of the input as an int.
			
			if(optionNumber == 1) {
				PopTable.populateFromWorkbook();
			}else if(optionNumber == 2) {
				SearchRow.searchRowByDate();
			}else if(optionNumber == 3) {
				InsertRow.insertRow();
			} else if(optionNumber == 4) {
				DeleteRow.deleteRowByDate();
			}else if(optionNumber == 5) {
				System.out.println("Exiting!");
				reader.close();
			}	
		}		
	}

}
