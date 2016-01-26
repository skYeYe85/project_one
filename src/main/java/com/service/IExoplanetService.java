package com.service;

import java.util.List;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;

public interface IExoplanetService {
	public Exoplanet create(Exoplanet e) throws ExoplanetServiceException;
	public Exoplanet read(Integer id) throws ExoplanetServiceException;
	public void update(Exoplanet e) throws ExoplanetServiceException;
	public void delete(Exoplanet e) throws ExoplanetServiceException;
	public List<Exoplanet> listAllExoplanets() throws ExoplanetServiceException;
}
