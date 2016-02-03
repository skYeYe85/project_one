package com.service;

import java.text.ParseException;
import java.util.List;

import com.dto.Exoplanet;

public interface IExoplanetPage {

	public List<Exoplanet> exoplanetSearchList(String searchText) throws ParseException;
}
