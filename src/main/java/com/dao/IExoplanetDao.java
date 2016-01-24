package com.dao;

import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;

public interface IExoplanetDao {
	public Exoplanet create(Exoplanet e) throws ExoplanetPersistenceException;
	public Exoplanet read(Exoplanet e) throws ExoplanetPersistenceException;
	public Exoplanet update(Exoplanet e) throws ExoplanetPersistenceException;
	public void delete(Exoplanet e) throws ExoplanetPersistenceException;
}
