package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static XSSFWorkbook _xssfworkBook; // >=2007
	public static XSSFSheet xssfSheet;
	public static XSSFRow xssfRow;
	public static XSSFCell xssfCell;

	public static HSSFWorkbook _hssWorkBook; // <2007
	public static HSSFSheet hssfSheet;
	public static HSSFRow hssfRow;
	public static HSSFCell hssfCell;

	public static Workbook workbook;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;

	public static String _filePath;

	// Constructor
	public ExcelUtility() {
	};

	/**
	 * Set an Excel workbook from based on file path.
	 */

	public static void setWorkbookExcel(String inFilePath) {
		_filePath = inFilePath;
		try {
			FileInputStream inputStream = new FileInputStream(_filePath);
			switch (FilenameUtils.getExtension(_filePath).toLowerCase()) {
			case "xlsx":
				workbook = new XSSFWorkbook(inputStream);
				sheet = xssfSheet;
				row = xssfRow;
				cell = xssfCell;
				break;

			case "xls":
				workbook = new HSSFWorkbook(inputStream);
				sheet = hssfSheet;
				row = hssfRow;
				cell = hssfCell;
				break;
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create manipulation methods working with Excel workbook.
	 */

	// Open excel file
	public static void openExcel(String path) throws IOException {
		_filePath = path;
		File file = new File(_filePath);
		if (file.canRead()) {
			setWorkbookExcel(_filePath);
		}
	}

	// Save excel file
	public static void saveExcel() throws IOException {

		FileOutputStream fileOutputStream = new FileOutputStream(_filePath);
		workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	// SaveAs excel file
	public static void saveAsExcel(String path) throws IOException {

		FileOutputStream fileOutputStream = new FileOutputStream(path);
		workbook.write(fileOutputStream);
		fileOutputStream.flush();
		fileOutputStream.close();
	}

	/**
	 * Create manipulation methods to work with Sheet of Excel file.
	 */

	// Check Sheet Exist
	public static boolean isSheetExist(String sheetname) {
		return (workbook.getSheetIndex(sheetname) >= 0);
	}

	// Create method getSheet To-Do
	public static Sheet getSheet(String sheetname) {
		Sheet sheet = workbook.getSheet(sheetname);
		if (!isSheetExist(sheetname)) {
			sheet = workbook.createSheet(sheetname);
		}
		return sheet;
	}

	// Add new sheet
	public static void addNewSheet(String sheetname) {
		if (!isSheetExist(sheetname)) {
			workbook.createSheet(sheetname);
		}
	}

	// Remove sheet by sheet-index
	public static void removeSheet(int sheetindex) {
		int i = workbook.getNumberOfSheets();
		if (i >= sheetindex && sheetindex > -1) {
			workbook.removeSheetAt(sheetindex);
		}
	}

	// Remove sheet by sheet-name
	public static void removeSheet(String sheetname) {
		if (workbook.getSheetIndex(sheetname) != -1) {
			int sheetindex = workbook.getSheetIndex(sheetname);
			removeSheet(sheetindex);
		}
	}

	/**
	 * Create manipulation methods to work with column of Excel file.
	 */

	// Enter value into column
	public static void enterColumnValue(String sheetname, String cellvalue) {
		sheet = getSheet(sheetname);
		addCellValue(sheet, cellvalue);
	}

	// Modify insert value in enterValue
	public static void addCellValue(Sheet sheet, String value) {

		row = sheet.getRow(0);
		if (row == null) {
			row = sheet.createRow(0);
		}
		if (row.getLastCellNum() == -1) {

			cell = row.createCell(0);
		} else {
			cell = row.createCell(row.getLastCellNum());

		}
		cell.setCellValue(value);

		addCellStyle(sheet, cell);
	}

	// Set style for cell
	public static void addCellStyle(Sheet sheet, Cell cell) {

		CellStyle style = workbook.createCellStyle();
		style.setFillForegroundColor(HSSFColor.AQUA.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		cell.setCellStyle(style);
	}

	// Remove column by index
	public static void removeColumnValue(String sheetname, int indexcolumn) {

		sheet = getSheet(sheetname);
		int rowCount = sheet.getLastRowNum();

		for (int i = 0; i <= rowCount; i++) {	//"=" last-row
			row = sheet.getRow(i);
			if (row != null) {
				cell = row.getCell(indexcolumn);
				if (cell != null) {
					row.removeCell(cell);

				}
			}
		}
	}

	// Remove column by name
	public static void removeColumnValue(String sheetname, String colname) {

		sheet = getSheet(sheetname);
		int indexCol = convertColumnNameToNumber(sheet, colname);
		removeColumnValue(sheetname, indexCol);
	}

	// Modify remove colume by convert col-name to index
	public static int convertColumnNameToNumber(Sheet sheet, String colname) {

		row = sheet.getRow(0);
		int cellRowNumber = row.getLastCellNum();
		int indexCol = -1;

		for (int i = 0; i < cellRowNumber; i++) { // Không có "="
			if (row.getCell(i).getStringCellValue().trim().equals(colname)) {
				indexCol = i;
			}
		}
		return indexCol;
	}
	
	/**
	 * Create manipulation methods to work with cell of Excel file.
	 */
	
	public static void setCell(Sheet sheet, int rowindex, int colindex, String value) {

		row = getRow(sheet, rowindex);
		cell = getCell(row, colindex);
		cell.setCellValue(value);
	}

	public static Row getRow(Sheet sheet, int rowindex) {
		row = sheet.getRow(rowindex - 1); // "-1" do tính từ 0
		if (row == null) {
			row = sheet.createRow(rowindex - 1);
		}
		return row;
	}

	public static Cell getCell(Row row, int colindex) {
		cell = row.getCell(colindex - 1);

		if (cell == null) {
			cell = row.createCell(colindex - 1);
		}
		return cell;

	}

	public static void setCell(String sheetname, int rowindex, int colindex, String value) {
		sheet = getSheet(sheetname);
		setCell(sheet, rowindex, colindex, value);
	}

	public static void setCell(String sheetname, String colname, int rowindex, String value) {
		sheet = getSheet(sheetname);
		int colIndex = convertColumnNameToNumber(sheet, colname) + 1;
		setCell(sheet, rowindex, colIndex, value);
	}

	// Get value of cell-indexcolumn
	public static String getCellValue(String sheetname, int rowindex, int colindex) {

		sheet = getSheet(sheetname);
		row = getRow(sheet, rowindex);
		cell = getCell(row, colindex);

		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		if (cell != null) {
			switch (evaluator.evaluateInCell(cell).getCellType()) {

			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			case Cell.CELL_TYPE_NUMERIC:
				return String.valueOf(cell.getNumericCellValue());
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_ERROR:
				return String.valueOf("Formula Error" + cell.getErrorCellValue());
			case Cell.CELL_TYPE_FORMULA:
				return String.valueOf(cell.getCellFormula());

			}
		}

		return null;
	}

	// Get value of cell-indexcolumn
	public static String getCellValue(String sheetname, String colname, int rowindex) {

		sheet = getSheet(sheetname);
		row = getRow(sheet, rowindex);
		int colindex = convertColumnNameToNumber(sheet, colname) + 1;
		cell = getCell(row, colindex);

		return getCellValue(sheetname, rowindex, colindex);
	}

	/**
	 * Get a list of all values from the specified column.
	 */

	public static ArrayList<String> getColumnValues(String sheetname, int index) {

		sheet = getSheet(sheetname);
		final int rowCount = getRowNumber();
		
		final ArrayList<String> cellValues = new ArrayList<String>();

		for (int i = 1; i < rowCount; i++) { // Exclude header
			try {
				String cellValue = sheet.getRow(i).getCell(index).getStringCellValue();
				cellValues.add(cellValue);
			} catch (Exception e) {
				cellValues.add("");
			}
		}
		return cellValues;
	}

	public static ArrayList<String> getColumnValuesArray(String sheetname, String colName) {

		sheet = getSheet(sheetname);
		int index = convertColumnNameToNumber(sheet, colName);
		final ArrayList<String> cellValues = getColumnValues(sheetname, index);

		return cellValues;
	}

	public static String[] getColumnValuesString(String sheetname, String colname) {

		ArrayList<String> arrayData = getColumnValuesArray(sheetname, colname);
		String[] strArr = new String[arrayData.size()];
		strArr = arrayData.toArray(strArr);

		return strArr;
	}

	public static int getRowNumber() {
		int rowNum = sheet.getLastRowNum();
		rowNum += 1; // Beware of header. Count from "0"
		return rowNum;
	}
	
	public static int getRowNumber(String sheetname) {
		sheet = getSheet(sheetname);
		int rowNum = sheet.getLastRowNum();
		rowNum += 1; // Beware of header. Count from "0"
		return rowNum;
	}

	public static int getColumnNumber() {
		int colNum = sheet.getRow(0).getLastCellNum();	// Count from "1"
		return colNum;
	}
	
	public static int getColumnNumber(String sheetname) {
		sheet = getSheet(sheetname);
		int colNum = sheet.getRow(0).getLastCellNum();	// Count from "1"
		return colNum;
	}

	/* --- END --- */
}
