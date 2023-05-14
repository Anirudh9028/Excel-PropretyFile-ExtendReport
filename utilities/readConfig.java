package ebanking.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class readConfig
{
	Properties pro ;
	
	String filePath = System.getProperty("user.dir")+"//TestData//loginData"; 
	

	public readConfig()
	{
		pro =new Properties();
		try
		{
		FileInputStream fis = new FileInputStream(filePath);
		
			try 
			{
				pro.load(fis);
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
		
		
		} 
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	} 
	
	
	public String getUrl() 
	{
		String url = pro.getProperty("baseUrl");
		if(url!=null) 
		{
			return url;
		}
		else {
			throw new RuntimeException("url is not specified in config file");
			}
	}
	
	public String getUsername() 
	{
		String Username = pro.getProperty("Username");
		if(Username!=null) 
		{
			return Username;
		}
		else {
			throw new RuntimeException("Username is not specified in config file");
			}
	}
	
	public String getPassword() 
	{
		String Password = pro.getProperty("Password");
		if(Password!=null) 
		{
			return Password;
		}
		else 
		{
			throw new RuntimeException("Password is not specified in config file");
			
		}
	}
	
	public String getHomePageTitle() 
	{
		String HomePageTitle = pro.getProperty("HomePageTitle");
		if(HomePageTitle!=null) 
		{
			return HomePageTitle;
		}
		else 
		{
			throw new RuntimeException("HomePageTitle is not specified in config file");
			
		}
	}
	
	
	
	
	
	
	
}
