package stepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.cucumber.java.en.Given;


public class excelAccessStepDef {

	FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	
	@Given("Excel workbook present")
	public void excel_workbook_present() throws IOException {
		
		file = new FileInputStream("C:\\TestData\\TestData.xlsx");
	    workbook = new XSSFWorkbook(file);
	    ArrayList<String> record = new ArrayList<String>();
	    
	    
	    XSSFSheet sheet = workbook.getSheet("Testcases");
	    
	    Iterator<Row> rows = sheet.iterator();  // This get the list of rows in sheet
	    
		while(rows.hasNext()) {
			
			Row r = rows.next();
			record = getRowData(r);
			if (record.get(0).equalsIgnoreCase("Two"))
				printRecord(record);
			
		}
	}
	
	public void printRecord(ArrayList<String> record) {
		int noOfItems = record.size();
		System.out.println("---");
		for (int i = 0; i < noOfItems;i++)
			System.out.println(record.get(i));
	}
	
	public ArrayList<String> getRowData(Row r) {
		ArrayList<String> record = new ArrayList<String>();
		Iterator<Cell> cell = r.iterator();  //This gets list of cells 
		
		while (cell.hasNext()) {
			Cell c = cell.next();
			if (c.getCellType() == CellType.STRING) {
				String cellData = c.getStringCellValue();
				record.add(cellData);	
			}else if (c.getCellType() == CellType.NUMERIC) {
				String cellData = NumberToTextConverter.toText(c.getNumericCellValue());
				record.add(cellData);
			}
		}
		return record;
	}

//	@When("user tries to access row {int}")
//	public void user_tries_to_access_row(Integer int1) {
//	   System.out.println("Step 2 ");
//	}
//
//	@Then("User is able to print the data")
//	public void user_is_able_to_print_the_data() {
//		System.out.println("Step 3 ");
//	}


}
