package testBase;
	
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import org.apache.logging.log4j.LogManager;

	public class BaseClass {
		
	public static WebDriver driver;
	public Logger logger;  //Log4j
	public Properties p;
	
		@BeforeClass(groups= {"Sanity", "Regression","Master", "DataDriven"})
		@Parameters({"OS","Browser"})
		public void setup(String OS, String br) throws IOException
		
		{
			//Loading config.properties
			FileReader file= new FileReader(".//src//test//resources//config.properties");
			p= new Properties();
			p.load(file);
			
			logger= (Logger) LogManager.getLogger(this.getClass());
			
			//Grid setup
			//If remote
			if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			{
				DesiredCapabilities cap= new DesiredCapabilities();
				//OS
				if(OS.equalsIgnoreCase("windows"))
				{
					cap.setPlatform(Platform.WIN11);
				}
				else
				{
					System.out.println("No matching OS");
					return;
				}
				
				//Browser
				switch(br.toLowerCase()) 
				{
				case "chrome": driver= new ChromeDriver(); break;
				case "edge": driver= new EdgeDriver(); break;
				case "firefox": driver= new FirefoxDriver(); break;
				default: System.out.println("Invalid browse name"); return;
				}
				
				driver= new RemoteWebDriver(new URL("http://192.168.1.13:4444/wd/hub"), cap);
				
			}
			//If local
			if(p.getProperty("execution_env").equalsIgnoreCase("local"))
			{
				
				switch(br.toLowerCase()) 
				{
				case "chrome": driver= new ChromeDriver(); break;
				case "edge": driver= new EdgeDriver(); break;
				case "firefox": driver= new FirefoxDriver(); break;
				default: System.out.println("Invalid browse name"); return;
				}
					
				
			}
			
			//driver =new ChromeDriver();
			//driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
			driver.get(p.getProperty("appURL"));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			
		}
		
		@AfterClass(groups= {"Sanity", "Regression","Master", "DataDriven"})
		public void tearDown()
		{
			driver.quit();
		}
		
		
		public String randomeString()
		{
			String generatedString=RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		public String randomeNumber()
		{
			String generatedNum=RandomStringUtils.randomNumeric(10);
			return generatedNum;
		}
		
		public String randomeAlphaNumeric()
		{
			String generatedString=RandomStringUtils.randomAlphabetic(3);
			String generatedNum=RandomStringUtils.randomNumeric(3);
			return generatedString+"@"+generatedNum;
		}
		
		public String captureScreen(String tname)
		{
			String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takess= (TakesScreenshot) driver;
			File sourceFile= takess.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath= System.getProperty("user.dir")+"\\screenshots\\"+ tname+ "_"+timeStamp+".png";
			File targetFile= new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
		}


	}


