package com.gy.server.http.config;


public interface HttpConfig {
	
	public  String getWelcomeFile();
	public  String getNotFoundFile();
	public  String getErrorFile();
	public  String getRootPath();
	public  String getResourcePath();
	public  int getPort();
}
