package com.gy.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ClientRequest {
	public static void main (String args []) throws IOException {
		URL url = new URL("http://localhost:8080"); 
		HttpURLConnection con = (HttpURLConnection) url.openConnection(); 
		con.setRequestMethod("GET"); 
//		// optional default is GET 
		con.setRequestProperty("User-Agent", "Mozilla/5.0"); 
//		// add request header 
//		con.connect();
		int responseCode = con.getResponseCode(); 
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); 
//		String inputLine; 
//		StringBuffer response = new StringBuffer(); 
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine); } in.close(); 
//			// print result 
//			System.out.println("HTTP 응답 코드 : " + responseCode); 
//			System.out.println("HTTP body : " + response.toString());
//
	}
}
