package com.amelio.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME=15;
	public static final int PAGE_LOAD_TIME=15;
	
	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp=date.toString().replaceAll(" ", "_").replaceAll(":", "_");
		return "amotoori"+timeStamp+"@gmail.com";
		
	}
	
public static Object[][] getTestDataFromExcel(String sheetName){
		
		File excelFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\amelio\\qa\\testdata\\amelioTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fixexcel= new FileInputStream(excelFile);
			workbook=new XSSFWorkbook(fixexcel);
			
		}catch(Throwable e) {
			e.getStackTrace();
		}
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		for(int i=0;i<rows;i++) {
			XSSFRow row=sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
			XSSFCell cell=	row.getCell(j);
			
			CellType celltype=cell.getCellType();
			
			switch(celltype) {
			case STRING:
				data[i][j]=cell.getStringCellValue();
				break;
			case NUMERIC:
				data[i][j]=Integer.toString((int)cell.getNumericCellValue());
				break;
			case BOOLEAN:
				data[i][j]=cell.getBooleanCellValue();
			}
			}
		}
		
		
		return data;
		
	}

}
