package com.gy.server.http.response;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import com.gy.custom.config.HttpConfig;
import com.gy.server.http.request.HttpGYRequest;
import com.gy.server.http.request.HttpRequest;

public class HttpResponse {
	
	HttpRequest request;
	Socket clientSocket;
	HttpConfig httpConfig;
	public HttpResponse (Socket clientSocekt,HttpRequest request,HttpConfig httpConfig) {
		this.request = request;
		this.clientSocket =clientSocekt;
		this.httpConfig = httpConfig;
		
		call();
	}
	
	public void call() {
		
		PrintWriter out = null; 
		BufferedOutputStream dataOut = null;
		try {
		out = new PrintWriter(clientSocket.getOutputStream());
		dataOut = new BufferedOutputStream(clientSocket.getOutputStream());
		
		File outFile;
		
		
		
		
		  
		
		if (request.getRequestUri() != null && request.getRequestUri().equals("/")) {
			outFile = new File(httpConfig.getWelcomeFile());
		}else if (request.getRequestUri() != null) {
			outFile = new File(httpConfig.getResourcePath(),request.getRequestUri());
			
			if (!outFile.exists()) {
				outFile = new File(httpConfig.getNotFoundFile());
			}	
		}else {
			outFile = new File(httpConfig.getErrorFile());
		}
		
		
		int fileLength = (int) outFile.length();
		String content = getContentType(outFile.getName());
		
		byte [] fff = readFileData(outFile,fileLength);
		
		//write headers
		out.println("HTTP/1.1 200 OK");
		out.println("Server: Java HTTP Server from GYApplication : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + content);
		out.println("Content-length: " + fileLength);
		out.println(); // blank line between headers and content, very important !
		out.flush(); // flush character output stream buffer
		
		dataOut.write(fff, 0, fileLength);
		dataOut.flush();
		
		}catch(Exception e) {
			
			e.printStackTrace();
		}finally {
		/*
			if (out!=null) {
				out.close();
			}
			if (dataOut !=null) {
				try {
					dataOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			*/
		}
		
		
		
	}
	
	// return supported MIME Types
	private String getContentType(String fileRequested) {
		if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}
	private byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) {
				fileIn.close();
			}
		}
		
		return fileData;
	}
	
	
	
}
