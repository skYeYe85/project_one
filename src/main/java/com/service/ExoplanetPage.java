package com.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.dto.Exoplanet;

public class ExoplanetPage implements IExoplanetPage {
	
	private static final String exoplanetCatalog = "http://exoplanet.eu/catalog/";
	private List<WebElement> exoplanetWebData;
	String planetCount = "";
	private static final By showAllResults = By.xpath("/html/body/div[2]/div[2]/div[3]/div/div[3]/label/select/option[4]");
	private static final By searchTextFieldLocator = By.xpath("/html/body/div[2]/div[2]/div[3]/div/div[4]/label/input");
	private static final By planetCountLocator = By.xpath("/html/body/div[2]/div[2]/p/span[2]");
	private WebDriver driver;

	public List<WebElement> exoplanetListSearch(String searchText) {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(exoplanetCatalog);
		driver.findElement(searchTextFieldLocator).clear();
		driver.findElement(searchTextFieldLocator).sendKeys(searchText);
		return null;
	}

	public List<Exoplanet> exoplanetWholeList() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(exoplanetCatalog);
		String planetCountInText = driver.findElement(planetCountLocator).getText();
		if(planetCountInText.startsWith("Showing ")){
			for(int i = 0; i < planetCountInText.length(); i++){
				if(planetCountInText.substring(i, i+1).equals("/")) break;
				if(Character.isDigit(planetCountInText.charAt(i))){
					planetCount += planetCountInText.substring(i, i+1);
				}
			}
		}
		driver.findElement(showAllResults).click();
		return null;
	}
	
	
	
}
