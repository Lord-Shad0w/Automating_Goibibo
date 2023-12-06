package baseClass;

import java.io.InputStream;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import locatorsUtility.HomepageLocators;
import utils.PropertiesFileReader;

//@Listeners(ListenerUtility.class)
public class BaseClass extends HomepageLocators {
	public static WebDriver driver;
	public static String browser;
	public static String email;
	public static String pass;
	public static WebDriverWait wait;
	public static InputStream configFile;
	public static InputStream credFile;
	public static PropertiesFileReader reader;
	public static String url ;
	public static Actions action;
	public static FluentWait<WebDriver> fwait;
	
	@BeforeTest
	public void configurations() throws Exception
	{
		configFile = this.getClass().getResourceAsStream("/config.properties");
		reader = new PropertiesFileReader(configFile);
		browser = reader.getValue("browser");
		url = reader.getValue("baseUrl");
		credFile = this.getClass().getResourceAsStream("/credential.properties");
		reader = new PropertiesFileReader(credFile);
		email = reader.getValue("Email");
		pass = reader.getValue("Password");
		
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.out.println("Chrome is being opened");
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			//To start the browser in maximized window
			co.addArguments("start-maximized");
			
			//To disable the notifications
			co.addArguments("--disable-notifications");
			driver = new ChromeDriver(co);
		} else if(browser.equalsIgnoreCase("firefox")) 
		{
			System.out.println("Firefox is being opened");
			
			WebDriverManager.firefoxdriver().setup();
			
			FirefoxBinary fb = new FirefoxBinary();
			FirefoxOptions fo = new FirefoxOptions();
			fo.setBinary(fb);
			
			//To disable the notifications
			fo.addPreference("dom.webnotifications.enable", false);
			
			//To disable the notification for location
			fo.addPreference("geo.enabled", false);
			driver =  new FirefoxDriver(fo);
			driver.manage().window().maximize();
		} else
		{
			System.out.println("Edge is being opened");
			
			WebDriverManager.edgedriver().setup();
			EdgeOptions eo = new EdgeOptions();
			
			//To maximize the browser window
			eo.addArguments("start-maximized");
			
			//To disable the notifications
			eo.addArguments("--disable-notifications");
			
			driver = new EdgeDriver();
		}		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		action = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		fwait = new FluentWait<WebDriver>(driver);
		driver.get(url);
		
			}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
		System.out.println(browser+" browser is closed successfully.");
	}
}
