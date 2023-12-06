package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class homeLoginPage extends BaseClass {
	
	@Test(priority=0)
	public void verifyhomePageTitle()
	{
		String title = driver.getTitle();
		assertEquals(title,"Goibibo - Best Travel Website. Book Hotels, Flights, Trains, Bus and Cabs with upto 50% off");
	}
	@Test(priority=1)
	public void verifyLoginButton()
	{
		driver.findElement(By.xpath("//*[@data-id='auth-flow-section']/span")).click();
		WebElement loginbtn = driver.findElement(By.className("login__tab_wrapper"));
		assertTrue(loginbtn.isEnabled());
		loginbtn.click();
		
	}
	@Test(priority=2)
	public void verifyMobileFieldBox()
	{
		WebElement mobile = driver.findElement(By.name("phone"));
		assertTrue(mobile.isDisplayed());
	}
	

	public void wrongMobileNo(String mobileNo)
	{
		
		String errormsg ;
		WebElement mobile = driver.findElement(By.name("phone"));
		mobile.sendKeys(mobileNo);
		String mno = mobile.getAttribute("value");
		mobile.sendKeys(Keys.ENTER);
		errormsg = driver.findElement(By.xpath("//form/p")).getText();
		if(mno.length()<10)
		{
			assertEquals(errormsg,"Please enter a 10 digit mobile number");
		}
		else
		{
			assertEquals(errormsg,"Please enter a valid mobile number");
		}
	}
	@Test(priority=3)
	public void correctMobileNo() {
		String mobileNo="7061577704";
		
		WebElement mobile = driver.findElement(By.name("phone"));
		mobile.sendKeys(mobileNo);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("successMsg__subTitle")));
		System.out.println(driver.findElement(By.className("successMsg__subTitle")).getText());
	}
	
	//---------------------------------------------------------------
	
	
}
