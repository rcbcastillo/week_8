package com.qa.demo.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SeleniumDemo {
	private WebDriver driver;
	
	@BeforeEach
	void init() {
		ChromeOptions options = new ChromeOptions(); // allow us to set flags when working with Chrome
		options.addArguments("--remote-allow-origins=*");
		// creates new window
		this.driver= new ChromeDriver(options); // driver we are using
		//maximises it
		this.driver.manage().window().maximize();
		}
	
	@Test
	void turtleTest() {
		//redirects browser to this address
		this.driver.get("https://www.bbc.co.uk/search");
		
		WebElement search = this.driver.findElement(By.cssSelector("#search-input"));
		
		search.sendKeys("turtle");
		search.sendKeys(Keys.ENTER);
		WebElement result = this.driver.findElement(By.cssSelector("#main-content > div.ssrcss-1v7bxtk-StyledContainer.enjd40x0 > div > div > ul > li:nth-child(1) > div > div > div.ssrcss-tq7xfh-PromoContent.e1f5wbog8 > div.ssrcss-1f3bvyz-Stack.e1y4nx260 > a"));
		assertEquals("The Turtle Dove Pilgrimage", result.getText());
	}
	
	@Test
	void penguinTest() {
		//redirects browser to this address
		this.driver.get("https://www.penguin.co.uk/search");
		
		WebElement search = this.driver.findElement(By.cssSelector("#header-search-input"));
		
		search.sendKeys("penguin");
		search.sendKeys(Keys.ENTER);
		WebElement result = this.driver.findElement(By.cssSelector("#react-tabs-1 > div > section:nth-child(1) > div.BookShelf_wrapper__Q72Fr.BookShelf_grid___AB7D > a:nth-child(1) > figure"));
		//assertEquals("Penguin Readers Level 2", result.getText());
		assertTrue(result.getText().contains("Penguin Readers Level 2"));
	}
	
	
	@AfterEach
	void tearDown() {
		this.driver.close();
		}
	
	

}
