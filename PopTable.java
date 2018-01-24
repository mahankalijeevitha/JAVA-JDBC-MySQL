package IOT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// source adaptation: https://www.mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
//populate the Comments table using the data from Excelsheet
public class PopTable {

	private static final String FILE_NAME = "workbook.xlsx";

	public static void populateFromWorkbook() {
		try {
			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			System.out.println("Testdb connection");
			DbConnection connection = new DbConnection();
			connection.connect();

			// iterate over each row
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();

				if(currentRow.getRowNum() > 0) {
					connection.insertRow(
					currentRow.getCell(0).getDateCellValue().getTime(),
					currentRow.getCell(1).getStringCellValue(),
					currentRow.getCell(2).getStringCellValue(),
					currentRow.getCell(3).getStringCellValue(),
					currentRow.getCell(4).getStringCellValue(),
					currentRow.getCell(5).getStringCellValue(),
					currentRow.getCell(6).getStringCellValue(),
					currentRow.getCell(7).getStringCellValue(),
					currentRow.getCell(8).getStringCellValue(),
					currentRow.getCell(9).getStringCellValue()
					);	
				}
				
//				Iterator<Cell> cellIterator = currentRow.iterator();
//				while (cellIterator.hasNext()) {
//
//					Cell currentCell = cellIterator.next();
//
//					// Since we know first column is a date!!
//					if (currentCell.getColumnIndex() == 0 && currentCell.getCellTypeEnum() == CellType.NUMERIC) {
//						Date dt = currentCell.getDateCellValue();
//						System.out.print(currentCell.getDateCellValue() + "--");
////						connection.insertRow(currentCell.getDateCellValue().getTime());
//					}
//					// rest being string or numeric
//					if (currentCell.getCellTypeEnum() == CellType.STRING) {
//						System.out.print(currentCell.getStringCellValue() + "--");
//					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
//						System.out.print(currentCell.getNumericCellValue() + "--");
//					}
//				}
			}
			workbook.close();
			connection.disconnect();
			System.out.println("Done Inserting from Workbook!");
		} catch (IOException e) {
			System.out.println("FILE NOT FOUND MAYBE!");
			e.printStackTrace();
		}

	}

}
