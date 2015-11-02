package dataset;

import org.testng.annotations.DataProvider;

import com.utilities.ExcelUtility;

import java.io.IOException;

public class ProviderData {
	
	@DataProvider(name = "infomation")
	public static Object[][] enterInfo() {
		return new Object[][] { 
				{ "name", "address", "email", "postcode" },
				{ "John", "vietnam", "email@yahoo.com", "70000" },
				{ "Peter", "england", "email@google.com", "76860" }};
	}
	
	@DataProvider(name = "dayoption")
	public static Object[][] selectDayOption() {
		return new Object[][] { 
				{ "Today" },
				{ "First day of the next week" },
				{ "First day of the next month" }};
	}

	
	@DataProvider(name = "exceldata")
	public static Object[][] excelData() throws IOException{
		
		String path = System.getProperty("user.dir")+"/properties/Dataset.xlsx";
		String sheetname = "Dataset";
		String[] header={ "name", "address", "email", "postcode" };
		
		// Open Excel-file
		ExcelUtility.openExcel(path);
		ExcelUtility.getSheet(sheetname);

		int rows= ExcelUtility.getRowNumber(sheetname) -1;	// Excludes header
		int cols= ExcelUtility.getColumnNumber();
		
		Object[][] data = new Object[rows][cols];
		for( int i = 0; i<rows;i++){
			data[i][0] = ExcelUtility.getCellValue(sheetname, header[0], (i+2));	// Excludes header
			data[i][1] = ExcelUtility.getCellValue(sheetname, header[1], (i+2));
			data[i][2] = ExcelUtility.getCellValue(sheetname, header[2], (i+2));
			data[i][3] = ExcelUtility.getCellValue(sheetname, header[3], (i+2));
		}
		
		return data;
	}
}