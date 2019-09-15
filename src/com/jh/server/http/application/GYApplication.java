package com.jh.server.http.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.jh.custom.config.HttpConfig;
import com.jh.custom.config.HttpConfigDefault;
import com.jh.server.http.request.HttpGYRequest;
import com.jh.server.http.request.HttpRequest;
import com.jh.server.http.response.HttpResponse;

class Client{
	
}
public class GYApplication implements Runnable{
	
	private Socket clientSocket;
	private HttpRequest request;
	private HttpResponse response;
	private static HttpConfig httpConfig;
	
	private static final Logger logger = Logger.getLogger(GYApplication.class);
	
	
	public GYApplication(Socket clientSocket,HttpConfig httpConfig){
		this.clientSocket = clientSocket;
		this.httpConfig = httpConfig;
	}
	
	public void run() {
		try {
			request = new HttpGYRequest(clientSocket);
			if (request.isNormal()) {
				logger.debug("request ########  :"+request.toString());	
				response = new HttpResponse(clientSocket,request,httpConfig);
			}
		}catch(Exception e) {
			logger.error("ERR (run) : "+e.getLocalizedMessage());
		}finally {
			closeClientSocket();
		}
	}
	public void closeClientSocket() {
		try {
			clientSocket.close();
		}catch(IOException e) {
			logger.error("ERR (closeClientSocket) : "+e.getLocalizedMessage());
		}
	}
}
