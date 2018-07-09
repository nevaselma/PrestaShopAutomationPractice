package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	WebDriver driver;
	Faker fake;
	

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}

	@Test
	public void loginTest() throws InterruptedException {
		fake = new Faker();
		String emailAddress = fake.internet().emailAddress();
		String name = fake.name().firstName();
		String lastName = fake.name().lastName();
		String passWord = fake.internet().password();

		driver.get("http://automationpractice.com");
		driver.findElement(By.xpath("//a[@class = 'login']")).click();
		driver.findElement(By.id("email_create")).sendKeys(emailAddress);
		driver.findElement(By.id("SubmitCreate")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(name);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(passWord);
		driver.findElement(By.id("address1")).sendKeys(fake.address().streetAddress());
		driver.findElement(By.id("city")).sendKeys(fake.address().city());
		Select select = new Select(driver.findElement(By.id("id_state")));
		Thread.sleep(2000);
		select.selectByValue("7");
		driver.findElement(By.id("postcode")).sendKeys("60558");
		driver.findElement(By.id("phone_mobile")).sendKeys(fake.phoneNumber().cellPhone());
		driver.findElement(By.id("alias")).clear();
		driver.findElement(By.id("alias")).sendKeys(emailAddress);
		driver.findElement(By.id("submitAccount")).click();
		Thread.sleep(2000);
		
		//sign out
		driver.findElement(By.linkText("Sign out")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("email")).sendKeys(emailAddress);
		driver.findElement(By.id("passwd")).sendKeys(passWord);
		driver.findElement(By.id("SubmitLogin")).click();

		String actual = driver.findElement(By.xpath("//div[@class = 'header_user_info']/a/span")).getText();
		String expected = name + " " + lastName;
		System.out.println(expected);
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
