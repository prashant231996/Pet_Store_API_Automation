package api.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	String path;
	public FileInputStream fis;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	public ExcelUtility(String ExcelFilePath) throws IOException
	{
		this.path=ExcelFilePath;
		fis=new FileInputStream(this.path);
		workbook=new XSSFWorkbook(fis);
	}
	
	public int getRowNum(String sheetName)
	{
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		return rowCount;
	}
	
	public int getColumnNum(String sheetName, int row)
	{
		sheet=workbook.getSheet(sheetName);
		int colNum=sheet.getRow(row).getLastCellNum();
		return colNum;
	}
	
	public String getCellData(String sheetName, int Row, int column)
	{
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(Row);
		cell=row.getCell(column);
		
		DataFormatter formatter=new DataFormatter();
		String data="";
		try {
			data=formatter.formatCellValue(cell);
		} catch (Exception e) {
			data="";
		}
		return data;
	}
	

}
