package com.daoTest;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dao.ExoplanetDao;
import com.dao.IExoplanetDao;
import com.dto.Exoplanet;

public abstract class AbstractDaoTest {
	protected Exoplanet exoplanet;
	protected IExoplanetDao exoplanetDao = new ExoplanetDao();
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    Date parsed;
    java.sql.Date sql;
}
