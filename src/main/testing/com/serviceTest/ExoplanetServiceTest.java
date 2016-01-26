package com.serviceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;

public class ExoplanetServiceTest extends AbstractServiceTest {
	
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
	public void createExoplanet() throws ExoplanetServiceException {
		exoplanet = exoplanetService.create(exoplanet);
		assertThat(exoplanetService.read(exoplanet.getId()).getId(), equalTo(exoplanet.getId()));
	}
	
	@Test(expected = NullPointerException.class)
	public void createExoplanetShouldFail() throws ExoplanetServiceException {
		exoplanet = null;
		exoplanet = exoplanetService.create(exoplanet);
	}
	
	@Test(expected = ExoplanetServiceException.class)
	public void createExoplanetShouldFail2() throws ExoplanetServiceException {
		exoplanet.setMasse(-2.0);
		exoplanet = exoplanetService.create(exoplanet);
	}
	
	@Test
	public void updateExoplanet() throws ExoplanetServiceException {
		exoplanet = exoplanetService.create(exoplanet);
		assertThat(exoplanetService.read(exoplanet.getId()).getId(), equalTo(exoplanet.getId()));
		exoplanet.setPlanet("Gliese updated");
		exoplanetService.update(exoplanet);
		assertThat(exoplanetService.read(exoplanet.getId()).getPlanet(), equalTo(exoplanet.getPlanet()));
	}
	
	@Test
	public void deleteExoplanet() throws ExoplanetServiceException{
		Integer exoplanetsNotDeleted = exoplanetService.listAllExoplanets().size();
		exoplanet = exoplanetService.create(exoplanet);
		assertThat(exoplanetService.listAllExoplanets().size(), equalTo(exoplanetsNotDeleted + 1));
		exoplanetService.delete(exoplanet);
		assertThat(exoplanetService.listAllExoplanets().size(), equalTo(exoplanetsNotDeleted));
	}

}
