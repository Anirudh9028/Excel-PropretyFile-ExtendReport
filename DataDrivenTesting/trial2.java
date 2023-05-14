package DataDrivenTesting;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class trial2 {

	
	@Test(dataProvider ="logindata")	
	public void loginTest(String user,String pwd,String exp)
	{
		System.out.println(user +"," + pwd + ","+ exp);
		
	}
	
	
	
	
	@DataProvider (name = "logindata")
	public String [][] testData() throws IOException
	{
		
		/* excel file path = E:\eclips pro file\TestNG\src\test\java\DataDrivenTesting\Book1.xlsx or .\\Book1.xlsx */
		
		String filePath = "E:\\eclips pro file\\TestNG\\src\\test\\java\\DataDrivenTesting\\Book1.xlsx";
		String fileName = "sheet1";
		 
		ExcelFileUtility exu = new ExcelFileUtility();
		
		int totalRowCount = exu.getRowCount(filePath ,fileName); // get the row count from excel
		int totalCellCount = exu .getCellCount(filePath, fileName, 1); // get cell count
		
		String logData [] [] = new String  [totalRowCount] [totalCellCount];
		
		for(int i = 1;i<=totalRowCount;i++) 
		{
			for (int j = 0;j<totalCellCount;j++) 
			{
				logData [i-1] [j] = exu.getCellData(filePath, fileName, i, j);
			}
		}
		
		
		
		return logData;
	}
	

}
