package com.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dao.ExoplanetDao;
import com.dao.IExoplanetDao;
import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;
import com.exception.ExoplanetServiceException;

public class ExoplanetService implements IExoplanetService {

	private static final Logger logger = LogManager.getLogger(ExoplanetService.class);
	private IExoplanetDao exoplanetDao = new ExoplanetDao();
	
	public Exoplanet create(Exoplanet e) throws ExoplanetServiceException {
		validateExoplanet(e);
		try {
			e = exoplanetDao.create(e);
		} catch (ExoplanetPersistenceException epe) {
			logger.error(epe.getMessage());
			throw new ExoplanetServiceException(epe.getMessage(), epe);
		}
		return e;
	}

	public Exoplanet read(Exoplanet e) throws ExoplanetServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Exoplanet update(Exoplanet e) throws ExoplanetServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Exoplanet e) throws ExoplanetServiceException {
		// TODO Auto-generated method stub

	}

	public List<Exoplanet> list(Exoplanet e) throws ExoplanetServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validateExoplanet(Exoplanet e) {
		// TODO Auto-generated method stub	
	}
}
