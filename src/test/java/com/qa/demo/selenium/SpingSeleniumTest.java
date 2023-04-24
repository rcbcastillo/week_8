package com.qa.demo.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:cat-schema.sql", "classpath:cat-data.sql"})
public class SpingSeleniumTest {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	void init() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		this.driver = new ChromeDriver(options);
		this.driver.manage().window().maximize();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	}
	
	@Test
	void testTitle() {
		this.driver.get("http://localhost:" + port + "/");
		WebElement title = this.driver.findElement(By.cssSelector("body > header > h1"));
		assertEquals("CATS", title.getText());
	}
	
	@Test
	void testGetAll() throws Exception{
		this.driver.get("http://localhost:" + port + "/");
		WebElement card = this.wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#output > div > div")));
		assertTrue(card.getText().contains("Mr Bigglesworth"));
	}
	
	@Test
	void testCreateClick() throws Exception{
		this.driver.get("http://localhost:" + port + "/");
		
		WebElement cardName = this.driver.findElement(By.cssSelector("#catName"));
		cardName.sendKeys("Mr Wright");
		WebElement cardLength = this.driver.findElement(By.cssSelector("#catLength"));
		cardLength.sendKeys("23");
		WebElement cardWhiskers = this.driver.findElement(By.cssSelector("#catForm > div:nth-child(5) > label"));
		cardWhiskers.click();
		WebElement cardEvil = this.driver.findElement(By.cssSelector("#catForm > div:nth-child(6) > label"));
		cardEvil.click();
		
		WebElement cardSubmit = this.driver.findElement(By.cssSelector("#catForm > div.mt-3 > button.btn.btn-success"));
		cardSubmit.click();
		
		WebElement card = this.wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#output > div:nth-child(2)")));
		assertTrue(card.getText().contains("Mr Wright"));
		
	}
	
	@AfterEach
	void tearDown() {
		this.driver.close();
	}

}
