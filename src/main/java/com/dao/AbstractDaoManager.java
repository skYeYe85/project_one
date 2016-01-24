package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Andreea on 29/11/2014.
 */
public abstract class AbstractDaoManager {
	
    /**
     * Object DataSource which could be used in all the DAOs
     */
    protected ConnectionManager cm = new ConnectionManager();
    protected Connection connection = cm.openConnection();

    /**
     * Object PreparedStatement which could be used in all the DAOs
     */
    protected PreparedStatement ps;

    /**
     *  Object ResultSet which could be used in all the DAOs
     */
    protected ResultSet rs;

    /**
     *  Object String which could be used in all the DAOs for writing Queries
     */
    protected String sql;
}
