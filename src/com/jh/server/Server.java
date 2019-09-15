package com.jh.server;

import java.io.File;
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
	
	Server(){
		configureServerEnv();
	}
	
	
	private void configureServerEnv(){
		logger.info("Configuring Server Evironment...");
		configureLog4j();
	}
	private void configureLog4j() {
		try {
			logger.info("Configuring log4j..  " +  new File("log4j", "log4j.properties").getPath());
			PropertyConfigurator.configure( new File("log4j", "log4j.properties").getPath());
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
