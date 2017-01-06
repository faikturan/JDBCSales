package com.faikturan.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionDB {
	
	private static final Logger log = LogManager.getLogger(ConnectionDB.class);
	
	public Properties loadProperties(){
		Properties properties = new Properties();
		InputStream input = null;
		try {
			input = getClass().getResourceAsStream("/db.properties");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
		
	}
}
