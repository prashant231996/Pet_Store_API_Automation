package api.datprovider;

import java.io.IOException;

import org.apache.poi.xssf.XLSBUnsupportedException;
import org.testng.annotations.DataProvider;

import api.utility.ExcelUtility;

public class TestDataDatProvider {
	
	@DataProvider(name="TestData")
	public String[][] getData() throws IOException
	{
		String filePath="./src/test/resources/Userdata.xlsx";
		ExcelUtility fileUtility=new ExcelUtility(filePath);
		int rowCount= fileUtility.getRowNum("Data");
		System.out.println("Row Number are:"+rowCount);
		int colCount= fileUtility.getColumnNum("Data", 1);
		System.out.println("Column Number are:"+colCount);
		
		String[][] testData=new String[rowCount][colCount];
		
		for(int i=1; i<=rowCount;i++)
		{
			for(int j=0;j<colCount;j++)
			{
				testData[i-1][j]=fileUtility.getCellData("Data", i, j);
			}
		}
		return testData;
	}

}
