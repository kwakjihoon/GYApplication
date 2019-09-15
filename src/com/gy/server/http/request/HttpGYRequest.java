package com.gy.server.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.google.common.base.Splitter;

public class HttpGYRequest implements HttpRequest{

	// default
	private String method;
	private String protocol;
	private String requestUri;
	private String remoteAddr;
	private int remotePort;
	private Map<String, String> headerMap;
	//private String queryString;
	private Map<String,List<String>> parameterMap;
	
	private Date timeStamp;
	
	private boolean isNormal = true;
	private boolean hasQueryString = false;
	
	private static final Logger logger = Logger.getLogger(HttpGYRequest.class);
	
	public HttpGYRequest(Socket clientSocket) {
		this.timeStamp = new Date();
		this.remoteAddr = clientSocket.getRemoteSocketAddress().toString();
		parseHeader(clientSocket);
	}

	private void parseHeader(Socket socket) {
		
		BufferedReader bufferedReader = null;
		InputStreamReader inputSteamReader = null;

		// parse header
		try {
		
			inputSteamReader = new InputStreamReader(socket.getInputStream());
			bufferedReader = new BufferedReader(inputSteamReader);
			parseHeaderFirstLine(bufferedReader.readLine());
			if (this.isNormal) {
				String headerLine;
				String keyValues[];
				this.headerMap = new HashMap<>();			
				while (!(headerLine = bufferedReader.readLine()).isEmpty()) {
					keyValues = headerLine.split(":", 2);
					if (keyValues.length == 2) {
						this.headerMap.put(keyValues[0].trim(), keyValues[1].trim());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}  finally {
			if (this.isNormal) {
				logger.info("(Success)End Generate Request Object");
			} else {
				//logger.info("(Fail)End Generate Request Object");
			}

		}

	}
	
	private void parseHeaderFirstLine(String line) {
		if (line == null) {
			isNormal = false;
			//logger.error("ERR (parseFirstHeaderLine): null header"+remoteAddress);
		}else if (line.equals("")) {
			isNormal = false;
			//logger.error("ERR (parseFirstHeaderLine): empty '' header"+remoteAddress);
		}else {
			
			try {
				StringTokenizer parsedLine = new StringTokenizer(line);
				this.method = parsedLine.nextToken().toUpperCase();
				
				String requestURIAndQueryString = parsedLine.nextToken();
				String [] requestURIAndQueryString_ = requestURIAndQueryString.split("\\?",2);
				
				//logger.debug(Arrays.toString(reqURI)+"  $#%^!@$#%@$%#%@$#%^$#@!$%^#@!$%^");
				
				this.requestUri = requestURIAndQueryString_[0];
				if (requestURIAndQueryString_.length >1) {
					hasQueryString = true;
					setParameterMap(requestURIAndQueryString_[1]);
				}
				this.protocol = parsedLine.nextToken();
			}catch(Exception e) {
				e.printStackTrace();
				logger.error("HEADER PARSING ERR " + e.getLocalizedMessage());
			}
			
		}
	}
	
	

	public String getMethod() {
		return method;
	}
	
	public String getProtocol() {
		return protocol;
	}
	

	// only if existed parameter ~
	private void setParameterMap(String queryString){
		List<String> paramList = Splitter.on('&')
				.trimResults().splitToList(queryString);
		Map<String,List<String>> parameterMap = new HashMap<>();
		for (String param : paramList) {
			String key	=  param.split("=",2)[0];
			String value = param.split("=",2)[1];
			if (parameterMap.containsKey(key)) {
				parameterMap.get(key).add(value);
			}else {
				parameterMap.put(key,new ArrayList<String>());
				parameterMap.get(key).add(value);
			}
		}
		this.parameterMap = parameterMap;
	}
	public Map<String, List<String>> getParameterMap() {
		return parameterMap;
	}
	public Iterator<String> getParameterNames() {
		if (hasQueryString()) {
			Set<String> ParamsSet = getHeaderMap().keySet();
			Iterator<String> iterator = ParamsSet.iterator();
			return iterator;
		}
		return null;
	}
	public List<String> getParameterValue(String parameterName) {
		if (hasQueryString()) {
			return parameterMap.get(parameterName);
		}
		return null;
	}
	//end
	
	public String getRequestUri() {
		return requestUri;
	}
	
	public String getRemoteAddr() {
		return remoteAddr;
	}
	
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public boolean isNormal() {
		return isNormal;
	}
	public boolean hasQueryString() {
		return hasQueryString;
	}
	
	
	
	
	public String getHeader(String headerName) {
		return getHeaderMap().get(headerName);
	}
	@Override
	public String toString() {
		return "HttpRequest [method=" + method + ", protocol=" + protocol + ", requestUri=" + requestUri
				+ ", remoteAddress=" + remoteAddr + ", headerMap=" + headerMap + ", parameterMap=" + parameterMap
				+ ", timeStamp=" + timeStamp + ", isNormal=" + isNormal + ", hasQueryString=" + hasQueryString + "]";
	}

	

}
