package com.dao;

import java.util.List;

import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;

public interface IExoplanetDao {
	public Exoplanet create(Exoplanet e) throws ExoplanetPersistenceException;

	public Exoplanet read(Integer id) throws ExoplanetPersistenceException;

	public void update(Exoplanet e) throws ExoplanetPersistenceException;

	public void delete(Exoplanet e) throws ExoplanetPersistenceException;

	public List<Exoplanet> listAllExoplanets() throws ExoplanetPersistenceException;
}
