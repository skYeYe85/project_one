package com.daoTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.text.ParseException;

import com.dto.Exoplanet;
import com.exception.ExoplanetPersistenceException;

public class ExoplanetDaoTest extends AbstractDaoTest {
	
	@Before
	public void setUp() throws ParseException {
		parsed = format.parse("20110210");
	    sql = new java.sql.Date(parsed.getTime());
		exoplanet = new Exoplanet(null, "Gliese 876 d", 0.017, 0.0, 1.94, 0.02080665,
				0.081,50.0, 0.004427, 2005, sql);
	}
	
	@After
	public void tearDown(){
		parsed = null;
		sql = null;
		exoplanet = null;
	}
	
	@Test
	public void createExoplanet() throws ExoplanetPersistenceException {
		exoplanet = exoplanetDao.create(exoplanet);
		assertThat(exoplanetDao.read(exoplanet.getId()).getId(), equalTo(exoplanet.getId()));
	}
	
	@Test(expected = ExoplanetPersistenceException.class)
	public void createExoplanetShouldFail() throws ExoplanetPersistenceException {
		exoplanet = null;
		exoplanet = exoplanetDao.create(exoplanet);
	}
	
	@Test
	public void updateExoplanet() throws ExoplanetPersistenceException {
		exoplanet = exoplanetDao.create(exoplanet);
		assertThat(exoplanetDao.read(exoplanet.getId()).getId(), equalTo(exoplanet.getId()));
		exoplanet.setPlanet("Gliese updated");
		exoplanetDao.update(exoplanet);
		assertThat(exoplanetDao.read(exoplanet.getId()).getPlanet(), equalTo(exoplanet.getPlanet()));
	}
	
	@Test
	public void deleteExoplanet() throws ExoplanetPersistenceException{
		Integer exoplanetsNotDeleted = exoplanetDao.listAllExoplanets().size();
		exoplanet = exoplanetDao.create(exoplanet);
		assertThat(exoplanetDao.listAllExoplanets().size(), equalTo(exoplanetsNotDeleted + 1));
		exoplanetDao.delete(exoplanet);
		assertThat(exoplanetDao.listAllExoplanets().size(), equalTo(exoplanetsNotDeleted));
	}
}
