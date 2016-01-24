package com.service;

import java.util.List;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;

public interface IExoplanetService {
	public Exoplanet create(Exoplanet e) throws ExoplanetServiceException;
	public Exoplanet read(Exoplanet e) throws ExoplanetServiceException;
	public Exoplanet update(Exoplanet e) throws ExoplanetServiceException;
	public void delete(Exoplanet e) throws ExoplanetServiceException;
	public List<Exoplanet> list(Exoplanet e) throws ExoplanetServiceException;
}
