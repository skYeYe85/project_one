package com.service;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;

public interface IExoplanetPage {

	public List<Exoplanet> exoplanetListAll(WebDriver driver) throws ExoplanetServiceException;

	public List<Exoplanet> exoplanetByName(WebDriver driver, String exoplanetName) throws ExoplanetServiceException;
}
