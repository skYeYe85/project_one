package com.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.jetty.log.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dto.Exoplanet;

public class ExoplanetPage implements IExoplanetPage {

	private static final String exoplanetCatalog = "http://exoplanet.eu/catalog/";
	private static final Integer numOfPlanetParameters = 10;
	private WebDriver driver;
	private List<WebElement> exoplanetWebData;
	private List<Exoplanet> exoplanetList;
	private static final By selectResultsLocator = By.xpath("/html/body/div[2]/div[2]/div[3]/div/div[3]/label/select");
	private static final By filterTextFieldLocator = By.xpath("/html/body/div[2]/div[2]/div[2]/form/div[2]/input");
	private static final By planetCountLocator = By.xpath("/html/body/div[2]/div[2]/p/span[2]");
	private static final By filterButtonLocator = By.xpath("/html/body/div[2]/div[2]/div[2]/form/div[3]/input");
	private static final By webDataLocator = By.xpath("//td");

	public List<Exoplanet> exoplanetListAll() throws ParseException {
		this.exoplanetWebData = exoplanetListSearch("");
		return exoplanetList(exoplanetWebData);
	}

	public List<Exoplanet> exoplanetByName(String exoplanetName) throws ParseException {
		String searchString = "\""+exoplanetName+" \" in name";
		this.exoplanetWebData = exoplanetListSearch(searchString);
		return exoplanetList(exoplanetWebData);
	}

	private List<Exoplanet> exoplanetList(List<WebElement> exoplanetWebData) throws ParseException {
		this.exoplanetList = new ArrayList<Exoplanet>();
		Double parameter; // all-Planet-Double Parameters
		Integer entdeckung;
		for (int i = 0; i < exoplanetWebData.size(); i += numOfPlanetParameters) {
			Exoplanet e = new Exoplanet();
			e.setPlanet(getParameter(exoplanetWebData, i)); // Planet
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 1)); // Masse
			e.setMasse(parameter);
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 2)); // Radius
			e.setRadius(parameter);
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 3)); // Periode
			e.setPeriode(parameter);
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 4)); // AstroEinheit
			e.setAstroEinheit(parameter);
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 5)); // Exzentrizitaet
			e.setExzentrizitaet(parameter);
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 6)); // Bahnneigung
			e.setBahnneigung(parameter);
			parameter = Double.parseDouble(getParameter(exoplanetWebData, i + 7)); // Winkelabstand
			e.setWinkelabstand(parameter);
			entdeckung = Integer.parseInt(getParameter(exoplanetWebData, i + 8)); // Entdeckung
			e.setEntdeckung(entdeckung);
			e.setAktualisierung(e.convertToSqlDate(exoplanetWebData.get(i + 9).getText())); // Aktualisierungsdatum
			this.exoplanetList.add(e);
		}
		return exoplanetList;
	}

	private List<WebElement> exoplanetListSearch(String queryText) {
		exoplanetWebData = new ArrayList<WebElement>();
		ignoreLogging();
//		driver = new FirefoxDriver();
		driver = new HtmlUnitDriver();
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to(exoplanetCatalog);
		WebElement dropDownListBox = driver.findElement(selectResultsLocator);
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByVisibleText("All");
		if (queryText.equals("")) {
			proceedWhenVisible(driver, 15);
			return this.exoplanetWebData = driver.findElements(webDataLocator);
		}
		driver.findElement(filterTextFieldLocator).clear();
		driver.findElement(filterTextFieldLocator).sendKeys(queryText);
		driver.findElement(filterButtonLocator).click();
		proceedWhenVisible(driver, 15);
		return this.exoplanetWebData = driver.findElements(webDataLocator);
	}

	private String getParameter(List<WebElement> list, int position) {
		String elementData = "";
		String noData = "—";
		if (position % numOfPlanetParameters == 0) {
			elementData = list.get(position).getText();
		} else if (position % numOfPlanetParameters < 8 || position % numOfPlanetParameters > 0) {
			elementData = list.get(position).getText();
			if (elementData.equals(noData))
				elementData = "0.0";
		} else if (position % numOfPlanetParameters == 8) {
			elementData = list.get(position).getText();
			if (elementData.equals(noData))
				elementData = "0";
		} else if (position % numOfPlanetParameters == 9) {
			// Datumsformat testen... ToDo ExoplanetPageException
		} else {
			// logger.info("something went wrong");
		}
		return elementData;
	}

	private void proceedWhenVisible(WebDriver driver, int timeout) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String planetCount = "";
		String planetCountInText = driver.findElement(planetCountLocator).getText();
		if (planetCountInText.startsWith("Showing ")) {
			for (int i = 0; i < planetCountInText.length(); i++) {
				if (planetCountInText.substring(i, i + 1).equals("/"))
					break;
				if (Character.isDigit(planetCountInText.charAt(i))) {
					planetCount += planetCountInText.substring(i, i + 1);
				}
			}
		}
		String xpath = "//tr[" + planetCount + "]//td[" + numOfPlanetParameters.toString() + "]";
		By lastWebElementLocator = By.xpath(xpath);
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastWebElementLocator));
	}

	private void ignoreLogging() {
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.OFF);
		Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		Logger.getLogger("org.apache.http").setLevel(Level.OFF);
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
//		if (!driver.toString().contains("(null)")) {
//			driver.close();
//		}
	}
}
