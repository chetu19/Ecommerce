package E_Commerce.DataDriven.FW.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;
	
	public void OpenBrowser(String bType) throws IOException{
		
		//initialize the properties file
		if(prop==null)
			prop= new Properties();
		
		//surrounding it with try catch block in case the properties file path gives an issue 
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//projectconfig.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
	
	if(bType.equals("Mozilla")){
		System.setProperty("webdriver.gecko.driver", prop.getProperty("Firefox_Exe"));
		driver=new FirefoxDriver();
			}
	if(bType.equals("Chrome")){
		System.setProperty("webdriver.chrome.driver", prop.getProperty("Chrome_Exe"));
		driver=new FirefoxDriver();
	}
	
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	
	public void navigate(String url){
		driver.get(prop.getProperty(url));
		
	}
	
	// Finding and locating the element
	public WebElement getElement(String locatorKey){
		WebElement e= null;
		//Surrounding with try-catch because if finding the element stalls, then it should fail
		try{
			
			if(locatorKey.endsWith("_id"))
			e= driver.findElement(By.id(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_name"))
			e=driver.findElement(By.name(prop.getProperty(locatorKey)));
			else if(locatorKey.endsWith("_xpath"))
				e=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
				
		}
		catch (Exception ex){
			reportfail(ex.getMessage());
			ex.printStackTrace();
			Assert.fail("Failed the test case "+ex.getMessage());
		}
		
		return null;
		
	}
	
	public void click(String locatorKey){
		driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		
	}
	
	public void type(String locatorKey, String msg){
		getElement(locatorKey).sendKeys(msg);
		
	}
	
	
/****************************** Validation *******************************/
	
	public void isElementPresent(){
		
	}
	
	
	public void verifyText(){
		
	}
	
	
	public void verifyTitle(){
		
	}
	
	/****************************** Reporting *******************************/
	
	public void takescreenshot(){
		
	}
	
	public void reportpass(){
		
	}
	
	public void reportfail(String msg){
		
	}
	
	
	
}
