package DataDrivenTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class normalDDT 
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
	public Object[][] testData()
	{
		Object [][] getData = {
				
				{"admin@yourstore.com","admin","valid"},
				{"admin@yourstore.com","admi","invalid"},
				{"admin@yourstor.com","admin","invalid"},
				{"admin@yourstore.com","adin","invalid"}
		};
		return getData;
	}
	
	
	
	
	
	
	
	@AfterClass
	public void tearDown() 
	{
		driver.close ();
	}
	
	
	
}
