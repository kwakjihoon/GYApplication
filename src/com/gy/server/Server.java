package com.gy.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import org.apache.log4j.PropertyConfigurator;


/**
 *
 * @author kwakjihoon
 *
 * 어플리케이션 서버 시스템 프로퍼티 설정
 */
public class Server{
	
	//java util logger
	Logger logger = Logger.getLogger(Server.class.getName());
	private static final File SERVER_CONFIG_PROPERTIES = new File("properties/server_config.properties");
	private static final String LOG4J_CONFIG_PATH;
	private static final int THREAD_COUNT;
	
	static {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(SERVER_CONFIG_PROPERTIES));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		THREAD_COUNT = Integer.parseInt(properties.getProperty("server.thread.count","10"));
		LOG4J_CONFIG_PATH = properties.getProperty("server.log4j.config.path","properties/log4j/log4j.properties");
	
	}
	protected static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(THREAD_COUNT);
	

	
	Server(){
		configureServerEnv();	
	}
	
	
	private void configureServerEnv(){
		logger.info("Initialization Server....");
		logger.info("Thread Count.." +THREAD_COUNT);
		logger.info("Configuring log4j..  " +  LOG4J_CONFIG_PATH);
		logger.info("Configuring Server Evironment...");
		configureLog4j();
		
		
	}
	private void configureLog4j() {
		try {
			PropertyConfigurator.configure(LOG4J_CONFIG_PATH);
		}catch(Exception e) {
			logger.severe(e.getLocalizedMessage());
		}
	}
	public void start() {
		 try {
			throw new Exception("Not Existed Custom Server..");
		} catch (Exception e) {
			
		}
	}
	
}
