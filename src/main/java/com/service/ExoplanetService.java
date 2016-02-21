package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.IExoplanetDao;
import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;
import com.exception.ExoplanetServiceException;

public class ExoplanetService implements IExoplanetService {

	private static final Logger logger = LogManager.getLogger(ExoplanetService.class);
	// first discovery of Exoplanet, Wikipedia
	private static final String firstDiscovery = "1988-01-01";
	@Autowired
	private IExoplanetDao exoplanetDao;

	public Exoplanet create(Exoplanet e) throws ExoplanetServiceException {
		checkExoplanetValidationService(e);
		try {
			e = exoplanetDao.create(e);
		} catch (ExoplanetPersistenceException epe) {
			serviceExceptionHandling(epe);
		}
		return e;
	}

	public Exoplanet read(Integer id) throws ExoplanetServiceException {
		return exoplanetExists(id);
	}
	
	public Exoplanet readByName(String planet) throws ExoplanetServiceException {
		Exoplanet e = null;
		try {
			e = exoplanetDao.readByName(planet);
		} catch (ExoplanetPersistenceException epe) {
			serviceExceptionHandling(epe);
		}
		checkExoplanetValidationService(e);
		return e;
	}

	public void update(Exoplanet e) throws ExoplanetServiceException {
		exoplanetExists(e.getId());
		try {
			exoplanetDao.update(e);
		} catch (ExoplanetPersistenceException epe) {
			serviceExceptionHandling(epe);
		}
	}

	public void delete(Exoplanet e) throws ExoplanetServiceException {
		exoplanetExists(e.getId());
		try {
			exoplanetDao.delete(e);
		} catch (ExoplanetPersistenceException epe) {
			serviceExceptionHandling(epe);
		}
	}

	public List<Exoplanet> listAllExoplanets() throws ExoplanetServiceException {
		List<Exoplanet> elist = null;
		try {
			elist = exoplanetDao.listAllExoplanets();
		} catch (ExoplanetPersistenceException epe) {
			serviceExceptionHandling(epe);
		}
		for (int i = 0; i < elist.size(); i++) {
			checkExoplanetValidationService(elist.get(i));
		}
		return elist;
	}

	private void serviceExceptionHandling(ExoplanetPersistenceException epe) throws ExoplanetServiceException {
		logger.error(epe.getMessage());
		throw new ExoplanetServiceException(epe.getMessage(), epe);
	}

	private Exoplanet exoplanetExists(Integer id) throws ExoplanetServiceException {
		Exoplanet e = null;
		try {
			e = exoplanetDao.read(id);
			checkExoplanetValidationService(e);
		} catch (ExoplanetPersistenceException epe) {
			serviceExceptionHandling(epe);
		}
		return e;
	}

	private void checkExoplanetValidationService(Exoplanet e) throws ExoplanetServiceException {
		if (e.getPlanet() == "")
			throw new ExoplanetServiceException();
		if (e.getMasse() < 0)
			throw new ExoplanetServiceException();
		if (e.getRadius() < 0)
			throw new ExoplanetServiceException();
		if (e.getPeriode() < 0)
			throw new ExoplanetServiceException();
		if (e.getAstroEinheit() < 0)
			throw new ExoplanetServiceException();
		if (e.getExzentrizitaet() < 0)
			throw new ExoplanetServiceException();
		if (e.getBahnneigung() < 0)
			throw new ExoplanetServiceException();
		if (e.getWinkelabstand() < 0)
			throw new ExoplanetServiceException();
		if (e.getEntdeckung() < 0)
			throw new ExoplanetServiceException();
		checkDate(e.getEntdeckung(), e.getAktualisierung());
	}

	private void checkDate(Integer entdeckung, String aktualisierung) throws ExoplanetServiceException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(aktualisierung);
			Date toCompare = new Date(); // current date
			if (date.after(toCompare))
				throw new ExoplanetServiceException();
			toCompare = sdf.parse(firstDiscovery);
			if (date.before(toCompare))
				throw new ExoplanetServiceException();
			sdf.applyPattern("yyyy");
			toCompare = sdf.parse(entdeckung.toString());
			if (date.before(toCompare))
				throw new ExoplanetServiceException();
		} catch (ParseException e) {
			throw new ExoplanetServiceException();
		}
	}
}
