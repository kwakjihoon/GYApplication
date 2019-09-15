package com.gy.server.http.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.gy.common.utils.PropertiesUtils;
import com.gy.server.GYServer;



public class HttpConfigDefault implements HttpConfig{
	
	private static final Properties HTTP_CONFIG_PROPERTIES = PropertiesUtils.readProperties("properties/http/http_config.properties");
	
	static final String WELCOME_FILE;
	static final String NOT_FOUND_FILE;
	static final String ERROR_FILE;
	
	static final String ROOT_PATH;
	static final String RESOURCE_PATH;
	
	static final int PORT;
	
	static final boolean isDefault = true;
	
	
	private static final Logger logger = Logger.getLogger(HttpConfigDefault.class);
	
	static {
		
		Date date = new Date();
		logger.info("["+date+"] Init http configuration [Default configuration]");
		
		ROOT_PATH = HTTP_CONFIG_PROPERTIES.getProperty("server.http.path.root","");
		RESOURCE_PATH = HTTP_CONFIG_PROPERTIES.getProperty("server.http.path.resource","resources/static/");
		
		WELCOME_FILE = RESOURCE_PATH+
				HTTP_CONFIG_PROPERTIES.getProperty("server.http.web.welcome-file","index.html");
		
		NOT_FOUND_FILE = RESOURCE_PATH+
				HTTP_CONFIG_PROPERTIES.getProperty("server.http.web.not-found-file","404.html");
		
		ERROR_FILE = RESOURCE_PATH+
				HTTP_CONFIG_PROPERTIES.getProperty("server.http.web.error-file","error.html");
	
		PORT = Integer.parseInt(HTTP_CONFIG_PROPERTIES.getProperty("server.http.port","8080"));
		
	}

	public HttpConfigDefault() {
		
		logger.info("PORT: \t\t"+PORT);
		logger.info("ROOT_PATH: \t"+ROOT_PATH);
		logger.info("RESOURCE_PATH: \t"+RESOURCE_PATH);
		logger.info("WELCOME_FILE: \t"+WELCOME_FILE);
		logger.info("NOT_FOUND_FILE: \t"+NOT_FOUND_FILE);
		logger.info("ERROR_FILE: \t\t"+ERROR_FILE);
	}

	public String getWelcomeFile() {
		return WELCOME_FILE;
	}

	public String getNotFoundFile() {
		return NOT_FOUND_FILE;
	}

	public String getErrorFile() {
		return ERROR_FILE;
	}

	public String getRootPath() {
		return ROOT_PATH;
	}

	public String getResourcePath() {
		return RESOURCE_PATH;
	}

	public int getPort() {
		return PORT;
	}
	
	
	
	
	
	



	

	
	
}
