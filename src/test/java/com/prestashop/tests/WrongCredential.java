package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WrongCredential {
	
	WebDriver driver;
	
	 @BeforeClass
	  public void setUp() {
	   WebDriverManager.chromedriver().setup();
	   driver = new ChromeDriver();
	   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	   driver.manage().window().fullscreen();
	  }
	
	 	@Test
	 	public void wrongCredentialTest() {
	 		driver.get("http://automationpractice.com");
	 		
	 		driver.findElement(By.xpath("//a[@class = 'login']")).click();
	 		driver.findElement(By.id ("email")).sendKeys("bestdayofmylife@best.com");
	 		driver.findElement(By.id("passwd")).sendKeys("abcdefchijklmn");
	 		driver.findElement(By.id("SubmitLogin")).click();
	 		
	 		WebElement failed = driver.findElement(By.xpath("//div[@class = 'alert alert-danger']/ol/li"));
	 		System.out.println(failed.getText());
	 		Assert.assertTrue(failed.isDisplayed());
	 		
	 		
	 	}
	 	
	 	@AfterClass
		public void tearDown() {
			driver.quit();
		}
}
