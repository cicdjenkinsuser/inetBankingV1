package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomer;
import com.inetbanking.pageObjects.HomePage;
import com.inetbanking.pageObjects.LoginPage;

public class Customer extends BaseClass {

	@Test
	public void AddNewCustomer() throws InterruptedException, IOException
	{
		driver.get(baseURL);
		logger.info("URL is opened");
		Thread.sleep(10000);
		driver.findElement(By.id("details-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(5000);
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is entered");
		lp.setPassword(password);
		logger.info("Password is entered");
		lp.ClickLogin();
		logger.info("Clicked Login button");
		
		if(driver.getTitle().contains("Guru99 Bank Manager"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}
		
		else
		{
			captureScreen(driver,"Login");
			Assert.assertTrue(false);
			logger.info("Login Test failed");
		}
		
		HomePage hp = new HomePage(driver);
		hp.clickNewCustomerLink();
		
		AddCustomer addcust = new AddCustomer(driver);
		logger.info("providing customer details....");
		
		
		addcust.custName("Sugam");
		addcust.custgender("male");
		addcust.custdob("10/15/1985");
		Thread.sleep(5000);
		addcust.custaddress("INDIA");
		addcust.custcity("HYD");
		addcust.custstate("AP");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");
		
		String email=randomestring()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("abcdef");
		addcust.custsubmit();
		
		Thread.sleep(3000);
		
		logger.info("validation started....");
		
		boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");
		
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
			System.out.println(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText());
		}
		else
		{
			logger.info("test case failed....");
			captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
		
	}
	
	
	
}
