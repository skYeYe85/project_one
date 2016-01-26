package com.service;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.dto.Exoplanet;

public interface IExoplanetPage {
	
	public List<WebElement> exoplanetListSearch(String searchText);
	public List<Exoplanet> exoplanetWholeList();
}
