package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class Login_MultipleUsers extends BaseClass {
	
	@Test(dataProvider = "LoginData")
	public void LoginMultipleUsers(String uname, String pass) throws InterruptedException {		
		driver.get(baseURL);
		logger.info("URL is opened");
		Thread.sleep(10000);
		driver.findElement(By.id("details-button")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("proceed-link")).click();
		Thread.sleep(5000);
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(uname);
		lp.setPassword(pass);
		lp.ClickLogin();

		if (isAlerPresent()==true) {
			logger.info("Login Failed");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
		}

		else {
			logger.info("Login Successful");
			lp.ClickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(true);
		}

	}
	

	public boolean isAlerPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}

	@DataProvider(name = "LoginData")
	String[][] GetData() throws IOException {
		String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";

		int rows = XLUtils.getRowCount(path, "Sheet1");
		int columns = XLUtils.getCellCount(path, "Sheet1", 1);

		String loginData[][] = new String[rows][columns];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j <= columns - 1; j++) {
				loginData[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}

		return loginData;

	}

}
