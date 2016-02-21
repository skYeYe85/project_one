package com.service;

import java.util.List;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;

public interface IExoplanetPage {

	public List<Exoplanet> exoplanetListAll() throws ExoplanetServiceException;

	public List<Exoplanet> exoplanetByName(String exoplanetName) throws ExoplanetServiceException;
}
