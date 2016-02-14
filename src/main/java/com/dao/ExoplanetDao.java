package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;

public class ExoplanetDao extends AbstractDaoManager implements IExoplanetDao {

	protected static final Logger logger = LogManager.getLogger(ExoplanetDao.class);
	@Autowired
	private DriverManagerDataSource dataSource;

	public Exoplanet create(Exoplanet e) throws ExoplanetPersistenceException {
		checkExoplanetValidationDao(e);
		sql = "INSERT INTO EXOPLANETS VALUES (NULL, ?,?,?,?,?,?,?,?,?,?);";
		try {
			ps = dataSource.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setExoplanet(e);
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			e.setId(rs.getInt(1));

		} catch (SQLException se) {
			daoExceptionHandling(se);
		}

		if (e.getId() == null)
			throw new IllegalArgumentException("Exoplanet id cannot be null");

		return e;
	}

	public Exoplanet read(Integer id) throws ExoplanetPersistenceException {
		if (id == null)
			throw new IllegalArgumentException("Exoplanet id cannot be null");
		Exoplanet e = null;
		sql = "SELECT * FROM EXOPLANETS WHERE ID = ?";
		try {
			ps = dataSource.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				e = getExoplanet(rs);
			}
		} catch (SQLException se) {
			daoExceptionHandling(se);
		}
		checkExoplanetValidationDao(e);
		return e;
	}

	public void update(Exoplanet e) throws ExoplanetPersistenceException {
		if (e.getId() == null)
			throw new IllegalArgumentException("Exoplanet id cannot be null");
		checkExoplanetValidationDao(e);
		sql = "UPDATE EXOPLANETS SET PLANET = ?, MASSE = ?, RADIUS = ?, PERIODE = ?,"
				+ "ASTROEINHEIT = ?, EXZENTRIZITAET = ?, BAHNNEIGUNG = ?, WINKELABSTAND = ?,"
				+ "ENTDECKUNG = ?, AKTUALISIERUNG = ? WHERE ID = ?";
		try {
			ps = dataSource.getConnection().prepareStatement(sql);
			setExoplanet(e);
			ps.setInt(11, e.getId());
			ps.executeUpdate();
		} catch (SQLException se) {
			daoExceptionHandling(se);
		}
	}

	public void delete(Exoplanet e) throws ExoplanetPersistenceException {
		if (e.getId() == null)
			throw new IllegalArgumentException("Exoplanet id cannot be null");
		checkExoplanetValidationDao(e);
		sql = "DELETE FROM EXOPLANETS WHERE ID = ?";
		try {
			ps = dataSource.getConnection().prepareStatement(sql);
			ps.setInt(1, e.getId());
			ps.executeUpdate();
		} catch (SQLException se) {
			daoExceptionHandling(se);
		}
	}

	public List<Exoplanet> listAllExoplanets() throws ExoplanetPersistenceException {
		List<Exoplanet> elist = new ArrayList<Exoplanet>();
		sql = "SELECT * FROM EXOPLANETS";
		try {
			ps = dataSource.getConnection().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Exoplanet e = getExoplanet(rs);
				elist.add(e);
			}
		} catch (SQLException se) {
			daoExceptionHandling(se);
		}
		for (int i = 0; i < elist.size(); i++) {
			checkExoplanetValidationDao(elist.get(i));
		}
		return elist;
	}

	private void setExoplanet(Exoplanet e) throws SQLException {
		ps.setString(1, e.getPlanet());
		ps.setDouble(2, e.getMasse());
		ps.setDouble(3, e.getRadius());
		ps.setDouble(4, e.getPeriode());
		ps.setDouble(5, e.getAstroEinheit());
		ps.setDouble(6, e.getExzentrizitaet());
		ps.setDouble(7, e.getBahnneigung());
		ps.setDouble(8, e.getWinkelabstand());
		ps.setInt(9, e.getEntdeckung());
		ps.setString(10, e.getAktualisierung());
	}

	private Exoplanet getExoplanet(ResultSet rs) throws SQLException {
		Exoplanet e = new Exoplanet();
		e.setId(rs.getInt("id"));
		e.setPlanet(rs.getString("planet"));
		e.setMasse(rs.getDouble("masse"));
		e.setRadius(rs.getDouble("radius"));
		e.setPeriode(rs.getDouble("periode"));
		e.setAstroEinheit(rs.getDouble("astroeinheit"));
		e.setExzentrizitaet(rs.getDouble("exzentrizitaet"));
		e.setBahnneigung(rs.getDouble("bahnneigung"));
		e.setWinkelabstand(rs.getDouble("winkelabstand"));
		e.setEntdeckung(rs.getInt("entdeckung"));
		e.setAktualisierung(rs.getString("aktualisierung"));
		return e;
	}

	private void daoExceptionHandling(SQLException e) throws ExoplanetPersistenceException {
		logger.info(e.getMessage());
		throw new ExoplanetPersistenceException();
	}

	private void checkExoplanetValidationDao(Exoplanet e) throws ExoplanetPersistenceException {
		if (e == null)
			throw new ExoplanetPersistenceException();
		if (e.getPlanet() == null)
			throw new ExoplanetPersistenceException();
		if (e.getMasse() == null)
			throw new ExoplanetPersistenceException();
		if (e.getRadius() == null)
			throw new ExoplanetPersistenceException();
		if (e.getPeriode() == null)
			throw new ExoplanetPersistenceException();
		if (e.getAstroEinheit() == null)
			throw new ExoplanetPersistenceException();
		if (e.getExzentrizitaet() == null)
			throw new ExoplanetPersistenceException();
		if (e.getBahnneigung() == null)
			throw new ExoplanetPersistenceException();
		if (e.getWinkelabstand() == null)
			throw new ExoplanetPersistenceException();
		if (e.getEntdeckung() == null)
			throw new ExoplanetPersistenceException();
		if (e.getAktualisierung() == null)
			throw new ExoplanetPersistenceException();
	}
}
