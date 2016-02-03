package com.serviceTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dto.Exoplanet;
import com.service.IExoplanetService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class AbstractServiceTest {
	protected Exoplanet exoplanet;
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	Date parsed;
	java.sql.Date sql;

	@Autowired
	protected IExoplanetService exoplanetService;
}
