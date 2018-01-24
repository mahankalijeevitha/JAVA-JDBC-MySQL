package IOT;

public class SearchRow {
	
	public static void searchRowByDate() {
		DbConnection connection = new DbConnection();
		connection.connect();
		System.out.println("SEARCHING ROW BY DATE-----");
		connection.searchRowbyDate();
		connection.disconnect();
	}

}
