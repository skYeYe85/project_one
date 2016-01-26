package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class AbstractDaoManager {
	
    /**
     * Object ConnectionManager which could be used in all the DAOs
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
