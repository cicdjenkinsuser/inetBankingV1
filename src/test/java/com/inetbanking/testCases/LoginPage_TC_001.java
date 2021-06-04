package com.inetbanking.testCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class LoginPage_TC_001 extends BaseClass {
	
	@Test
	public void Login() throws InterruptedException, IOException
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
		
	}
}
