package IOT;

public class DeleteRow {
	
	public static void deleteRowByDate() {
		DbConnection connection = new DbConnection();
		connection.connect();
		System.out.println("DELETING ROW BY DATE-----");
		connection.deleteRowbyDate();
		connection.disconnect();
	}
}
