package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;

public class ExoplanetDao extends AbstractDaoManager implements IExoplanetDao {
	
    /*protected ConnectionManager cm = new ConnectionManager();
    protected Connection connection = cm.openConnection();
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected String sql;*/
	
	protected static final Logger logger = LogManager.getLogger(ExoplanetDao.class);

	public Exoplanet create(Exoplanet e) throws ExoplanetPersistenceException {
		checkExoplanetValidation(e);
		sql = "INSERT INTO EXOPLANETS VALUES (NULL, ?,?,?,?,?,?,?,?,?,?);";
		try {
			ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, e.getPlanet());
			ps.setDouble(2, e.getMasse());
			ps.setDouble(3, e.getRadius());
			ps.setDouble(4, e.getPeriode());
			ps.setDouble(5, e.getAstroEinheit());
			ps.setDouble(6, e.getExzentrizitaet());
			ps.setDouble(7, e.getBahnneigung());
			ps.setDouble(8, e.getWinkelabstand());
			ps.setInt(9, e.getEntdeckung());
			ps.setDate(10, e.getAktualisierung());
			
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            e.setId(rs.getInt(1));
            
		} catch (SQLException se) {
			logger.info(se.getMessage());
			throw new ExoplanetPersistenceException();
		}
		return e;
	}

	public Exoplanet read(Exoplanet e) throws ExoplanetPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Exoplanet update(Exoplanet e) throws ExoplanetPersistenceException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Exoplanet e) throws ExoplanetPersistenceException {
		// TODO Auto-generated method stub

	}

	private void checkExoplanetValidation(Exoplanet e) {
		// TODO Auto-generated method stub

	}
}
