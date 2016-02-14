package com.service;

import java.util.List;

import com.dto.Exoplanet;

public interface IExoplanetPage {

	public List<Exoplanet> exoplanetListAll();

	public List<Exoplanet> exoplanetByName(String exoplanetName);
}
