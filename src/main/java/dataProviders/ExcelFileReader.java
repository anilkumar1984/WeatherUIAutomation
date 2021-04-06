package dataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileReader {

	private XSSFWorkbook wb;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private int columnNumber;
	private String cellData;
	private FileInputStream inputStream;
	private File file;
	private FileOutputStream outputStream;
	private XSSFRow row;

	public ExcelFileReader(String sheetName) {

		file = new File("./TestData/TestData.xlsx");
		try {
			inputStream = new FileInputStream(file);
			wb = new XSSFWorkbook(inputStream);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		sheet = wb.getSheet(sheetName);

	}

	public String getCellData(String columnName, int rowNumber) {
		
		int columnCount = sheet.getRow(0).getLastCellNum();
		for (int j = 0; j < columnCount; j++) {
			String cellDataHeader = sheet.getRow(0).getCell(j).getStringCellValue();
			if (cellDataHeader.equalsIgnoreCase(columnName)) {
				columnNumber = j;
				break;
			}
		}

		cellData = sheet.getRow(rowNumber).getCell(columnNumber).getStringCellValue();
		//System.out.println("celldata in " + cellData);
		
		
		return cellData;

	}

	public int getRowCount() {
		
		
		return sheet.getPhysicalNumberOfRows();
		
		
	}
	
	public void createRow(int lastRowNum) {
		row=sheet.createRow(lastRowNum);
	}
	
	public void enterCellData(int columnIndex,String value) {
		
		XSSFCell cell=row.createCell(columnIndex);
		cell.setCellValue(value);
		
	}
	
	public void writeWorkBook() {
		
		
		try {
			outputStream = new FileOutputStream(new File("./TestData/TestData.xlsx"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeWorkBook() {
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
