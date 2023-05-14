package ebanking.utilities;



import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import ebanking.baseClass.setUpBaseClass;

public class report extends setUpBaseClass implements ITestListener
{
	//WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	
	
	String folderName ="TestReports";
	String reportName ="E-banking Assingment";
	String DocTitle ="E-banking";
	
	public void configureExtentReport() 
	{
		htmlReporter = new ExtentSparkReporter(".\\"+folderName+"\\"+reportName+".html");// folder name + file name+file extension
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		/*Add system info/environment info to reports*/
		
		reports.setSystemInfo("Machine", "Lenovo");
		reports.setSystemInfo("OS", "Window 10");
		reports.setSystemInfo("QA", "Anirudh");
		
		/*Configuration to change look report*/
		
		htmlReporter.config().setDocumentTitle(DocTitle);
		htmlReporter.config().setReportName(reportName);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM, dd, yyyy, hh:mm a '('zzz')'");
		
		
	}
	
	
	public  String TakeScreenShot(WebDriver driver , String ScreenShotName)  
	
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		// takeScreenShot
		
		TakesScreenshot ts =(TakesScreenshot)driver;
		//TakesScreenshot ts =driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);// its method take screen shot in a file format
		
		String destination = System.getProperty("user.dir")+ folderName + ScreenShotName + dateName +".png"; // current dir + folder name
		
		File trg = new File(destination); // file to be save = file path + filename+ file extension
	
		try 
		{
			FileUtils.copyFile(src,trg);// .copyFile(src,trg)= used for copy to folder
		} 
		catch (IOException e)
		{
			
			e.printStackTrace();
		}   
		
		return destination;
	
	}
	
	
	//This belong to ITestListener and It will Execute Before Starting Of Test set/Batch
	public void onStart(ITestContext Result)
	{
		configureExtentReport();
		System.out.println("Starts Test Execution...." ); 
	}

	//This belongs to ITestListner and it will execute after Starting Of Test set/Batch
	public void onFinish(ITestContext Result) 
	{
		reports.flush();
		System.out.println("Finish Test Execution....");
	}
	
	//This Belongs To ITestListener and will execute  before every the main test start ie. @Test
	public void onTestStart(ITestResult Result) 
	{
		System.out.println("Starts test....."+Result.getName());
	}

	//This Belongs To ITestListener and it will execute when a test is skipped 
	public void onTestSkipped(ITestResult Result) 
	{
		System.out.println("Skipped test: "+Result.getName());
		
		
		String screenShotPath = TakeScreenShot(driver, Result.getName());
		
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped Test Case is: "+ "<b><i>"+ Result.getName()+"</i></b>", ExtentColor.YELLOW));
		test.skip(MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath,Result.getName()).build());
		//fail(Result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath,Result.getName()).build());	
	}
	
	// This belongs to ITlisteners and it will execute when test is passed
	public void onTestSuccess(ITestResult Result) 
	{
		System.out.println("Passed test: "+Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Pass Test Case is: "+ Result.getName(), ExtentColor.GREEN));
	}
	

	// This belongs to ITlisteners and it will execute when test is failed
	public void onTestFailure(ITestResult Result) 
	
	{
		System.out.println("Failed test: "+Result.getName());
		
		Markup markup = MarkupHelper.createLabel("Important Test", ExtentColor.BLUE);
	
	
		
		String screenShotPath = TakeScreenShot(driver, Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the Failed Test Case is:   "+"<b><i>"+ Result.getName()+"</i></b>", ExtentColor.RED));
		test.fail(Result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath,Result.getName()).build());	
		
		
	}

}
