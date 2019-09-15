package com.jh.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.jh.custom.config.HttpConfig;
import com.jh.custom.config.HttpConfigDefault;
import com.jh.server.http.application.GYApplication;

public class GYServer extends Server{

	private static final int THREAD_CNT = 10;
	private static final ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);
	
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
					
					threadPool.submit(client);
					
					
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
