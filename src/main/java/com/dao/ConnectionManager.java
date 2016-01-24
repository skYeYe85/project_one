package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public ConnectionManager(){}
	
	public Connection openConnection(){
		try{
			return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/db", "sa", "");
		}catch (SQLException e){
			e.printStackTrace();
		} return null;
	}
	
	public void closeConnection(Connection c){
		if(c==null) return;
		try{
			if(c.isClosed()) return;
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
