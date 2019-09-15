package com.jh.server.http.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import org.apache.log4j.Logger;

public class HttpRequest {

	// default
	private String method;
	private String protocol;
	private Map<String, String> query;
	private String uri;
	private String remoteAddress;
	private Map<String, String> headerMap;
	private boolean isNormal = true;

	private static final Logger logger = Logger.getLogger(HttpRequest.class);
	public HttpRequest(Socket clientSocket) {
		
		this.remoteAddress = clientSocket.getRemoteSocketAddress().toString();
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
				logger.info("(Fail)End Generate Request Object");
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
			StringTokenizer parsedLine = new StringTokenizer(line);
			this.method = parsedLine.nextToken().toUpperCase();
			this.uri = parsedLine.nextToken();
			this.protocol = parsedLine.nextToken();
		}
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

	public boolean isNormal() {
		return isNormal;
	}

	

	@Override
	public String toString() {
		return "HttpRequest [method=" + method + ", protocol=" + protocol + ", query=" + "query" + ", uri=" + uri
				+ ", remoteAddress=" + getRemoteAddress() + ", headerMap=" + headerMap + "]";
	}

}
