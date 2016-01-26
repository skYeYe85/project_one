package com.serviceTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dto.Exoplanet;
import com.service.ExoplanetService;
import com.service.IExoplanetService;

public abstract class AbstractServiceTest {
	protected Exoplanet exoplanet;
	protected IExoplanetService exoplanetService = new ExoplanetService();
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    Date parsed;
    java.sql.Date sql;
}
