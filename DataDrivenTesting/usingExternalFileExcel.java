package DataDrivenTesting;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class usingExternalFileExcel 
{
	WebDriver driver;

	@BeforeClass
	public void setUp() 
	{
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--disable-notification");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(opt);
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	
	@Test(dataProvider ="logindata")	
	public void loginTest(String user,String pwd,String exp)
	{
		driver.get("https://admin-demo.nopcommerce.com/login");
		
		WebElement userName = driver.findElement(By.xpath("//input[@id='Email']"));
		userName.clear();
		userName.sendKeys(user);
		
		
		WebElement pwsd = driver.findElement(By.xpath("//input[@id='Password']"));
		pwsd.clear();
		pwsd.sendKeys(pwd);
		
		driver.findElement(By.xpath("// button[text()= 'Log in']")).click();// login btn
		
		
		String actualtitle = "Dashboard / nopCommerce administration";
		String ExpResult = driver.getTitle();
		
		
		if(exp.equals("valid")) 
		{
			if(actualtitle.equals(ExpResult)) 
			{
				Assert.assertTrue(true);
				driver.findElement(By.xpath("//a[text() ='Logout']")).click();
			}
			else {Assert.assertTrue(false);}
		}
		
		else if(exp.equals("invalid")) 
		{
			if(actualtitle.equals(ExpResult)) 
			{
				Assert.assertTrue(false);
				driver.findElement(By.xpath("//a[text() ='Logout']")).click();
			}
			else {Assert.assertTrue(true);}
		}

		
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
	
	
	
	
	
	
	
	@AfterClass
	public void tearDown() 
	{
		driver.close ();
	}

}
