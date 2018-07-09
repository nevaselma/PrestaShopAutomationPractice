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

public class BlankPassword {
	
	WebDriver driver;
	
	
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
		}

		@Test
		public void blankPassword() {
			driver.get("http://automationpractice.com");
	 		driver.findElement(By.xpath("//a[@class = 'login']")).click();
	 		driver.findElement(By.id ("email")).sendKeys("abcdefg@abc.com");
	 		driver.findElement(By.id("passwd")).sendKeys("");
	 		driver.findElement(By.id("SubmitLogin")).click();
	 		
	 		WebElement paswordRequired = driver.findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
	 		System.out.println(paswordRequired.getText());
	 		Assert.assertTrue(paswordRequired.isDisplayed());
			
			
		}
		
		@AfterClass
		public void tearDown() {
			driver.quit();
		}
}
