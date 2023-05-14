package ebanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFile {
	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static Workbook wb;
	public static Sheet ws;
	public static Row row;
	public static Cell cell;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fis = new FileInputStream(xlfile);
		wb = WorkbookFactory.create(fis);
		ws = wb.getSheet(xlsheet);

		int rowcount = ws.getLastRowNum();

		wb.close();
		fis.close();
		return rowcount;

	}

	public static int getPhysicalRowCount(String xlfile, String xlsheet) throws IOException {
		fis = new FileInputStream(xlfile);
		wb = WorkbookFactory.create(fis);
		ws = wb.getSheet(xlsheet);

		int physicalrowcount = ws.getPhysicalNumberOfRows();

		wb.close();
		fis.close();
		return physicalrowcount;

	}

	public static int getCellCount(String xlfile, String xlsheet, int rowcount) throws IOException

	{
		fis = new FileInputStream(xlfile);
		wb = WorkbookFactory.create(fis);
		ws = wb.getSheet(xlsheet);

		row = ws.getRow(rowcount);
		int cellcount = row.getLastCellNum();

		wb.close();
		fis.close();
		return cellcount;

	}

	public static int getPhysicalCellCount(String xlfile, String xlsheet, int rowcount) throws IOException

	{
		fis = new FileInputStream(xlfile);
		wb = WorkbookFactory.create(fis);
		ws = wb.getSheet(xlsheet);

		row = ws.getRow(rowcount);
		int physicalcellcount = row.getPhysicalNumberOfCells();

		wb.close();
		fis.close();
		return physicalcellcount;

	}
	
	
	public static String getCellDate(String xlfile, String xlsheet,int rowNum ,int columnNum) throws IOException
	{
		fis = new FileInputStream (xlfile);
		wb = WorkbookFactory.create(fis);
		ws = wb.getSheet(xlsheet);
		
		row = ws.getRow(rowNum);
		cell =row.getCell(columnNum);
		
		String data;
		try 
		{
			DataFormatter formatter =new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch (Exception e) 
		{
			data= "" ;
		}
		wb.close();
		fis.close();
		return data;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
