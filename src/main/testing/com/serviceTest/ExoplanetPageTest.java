package com.serviceTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.dto.Exoplanet;
import com.exception.ExoplanetServiceException;

public class ExoplanetPageTest extends AbstractServiceTest {

	@Test
	public void createExoplanet() throws ExoplanetServiceException {
		List<Exoplanet> list;
		int currentSize = exoplanetService.listAllExoplanets().size();
		driver = new FirefoxDriver();
		list = exoplanetPage.exoplanetByName(driver, "OGLE");
		driver.close();
		for (int i = 0; i < list.size(); i++) {
			exoplanet = exoplanetService.create(list.get(i));
		}
		assertThat(exoplanetService.listAllExoplanets().size(), equalTo(list.size() + currentSize));
	}
}
