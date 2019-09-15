package com.gy.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.gy.custom.config.HttpConfig;
import com.gy.custom.config.HttpConfigDefault;
import com.gy.server.http.application.GYApplication;

public class GYServer extends Server{

	
	private static HttpConfig httpConfig;
	private static final Logger logger = Logger.getLogger(GYServer.class);
	
	public GYServer(){
		super();
		init();
	}
	public GYServer(HttpConfig httpConfigCustom) {
		super();
		init(httpConfigCustom);
	}
	public void init() {
		this.httpConfig = new HttpConfigDefault();
	}
	public void init(HttpConfig httpConfigCustom) {
		this.httpConfig = httpConfigCustom;
	}
	
	public void start() {
		
		 
		try {
			ServerSocket serverSocket = new ServerSocket(httpConfig.getPort());
			logger.info("Start server..");
			
			while (true) {
				GYApplication client = null;
				try {
					client = new GYApplication(serverSocket.accept(), httpConfig);
					
					THREAD_POOL.submit(client);
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
