package com.serviceTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dto.Exoplanet;
import com.service.IExoplanetPage;
import com.service.IExoplanetService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractServiceTest {
	protected Exoplanet exoplanet;
	@Autowired
	protected IExoplanetService exoplanetService;
	@Autowired
	protected IExoplanetPage exoplanetPage;
}
