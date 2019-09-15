package com.jh.server.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class HttpRequest_ {
	
	//default
	private String method;
	private String protocol;
	private Map<String,String> query;
	private String uri;
	private String remoteAddress;
	private Map<String,String> headerMap;
	
	private boolean isNormal =true;
	public boolean isNormal() {
		return isNormal;
	}

	public HttpRequest_(Socket socket){
		
		
		
		
		this.remoteAddress = socket.getRemoteSocketAddress().toString();
		readHeaders(socket);
		
		
		
	}
	
	private void readHeaders(Socket socket) {
		
		
		BufferedReader bufferedReader = null;
		InputStreamReader inputSteamReader = null;
		
		//read headers
		try {
			String ii =socket.getRemoteSocketAddress().toString();
			
		
			
			if (socket.getInputStream().available() != 0 ) {
				
			inputSteamReader = new InputStreamReader(socket.getInputStream());
			
			bufferedReader = new BufferedReader(inputSteamReader);
			System.out.println("["+ii+"]read inputstream ....1");
			
			
			String headerFirstLine ="";
			if ( (headerFirstLine = bufferedReader.readLine()) == null) {
				
			}
			
			
			
			
			
			
			
			System.out.println("["+ii+"]"+headerFirstLine);
			System.out.println("["+ii+"]end read inputstream ....2");
			
			parseFirstHeaderLine(headerFirstLine);
			
			String headerLine;
			String keyValues [];
			this.headerMap = new HashMap<>();
			while (! ( headerLine = bufferedReader.readLine()).isEmpty() ) {				
				
				//System.out.println("["+ii+"]"+headerLine);
				keyValues = headerLine.split(":",2);
				
				
				if ( keyValues.length==2 ) {
					this.headerMap.put(keyValues[0].trim(), keyValues[1].trim());
				}
			}
			}else {
				System.out.println("bad request"+socket.getRemoteSocketAddress());
				System.out.println("bad request"+socket.getReceiveBufferSize());
				this.isNormal = false;
				throw new Exception("Null Header");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			
			if (this.isNormal) {
				System.out.println("(Success)End Generate Request Object");
			}else {
				System.out.println("(Fail)End Generate Request Object");
			}
			
		}
		
	}
	
	private void parseFirstHeaderLine(String line) throws Exception {
		if (line == null|| line.equals("")) {
			throw new Exception("error: parseFirstHeaderLine");
		}
		StringTokenizer parsedLine = new StringTokenizer(line);
		this.method = parsedLine.nextToken().toUpperCase();
		this.uri = parsedLine.nextToken();
		this.protocol = parsedLine.nextToken();
	}

	public String getMethod() {
		return method;
	}

	public String getProtocol() {
		return protocol;
	}

	public Map<String, String> getQuery() {
		return query;
	}

	public String getUri() {
		return uri;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	@Override
	public String toString() {
		return "HttpRequest [method=" + method + ", protocol=" + protocol + ", query=" + "query" + ", uri=" + uri
				+ ", remoteAddress=" + remoteAddress + ", headerMap=" + headerMap + "]";
	}
	
	
	
	
	
}
