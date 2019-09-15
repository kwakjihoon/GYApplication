package com.gy.server.http.application;
/*
package com.jh.server.http.application;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import com.jh.custom.config.HttpConfig;
import com.jh.custom.config.HttpConfigDefault;
import com.jh.custom.config.HttpConfig;
import com.jh.server.http.request.HttpGYRequest;

public class MainApplication  extends GYApplication implements Runnable {


	
	
	
	MainApplication (Socket socket ){
		super(socket);
	}
	
	
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		HttpConfig httpConfig = new HttpConfigDefault();
		
		ServerSocket serverSocket=null;
		Thread thread =null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		
		
		
		
		try {
			serverSocket = new ServerSocket(httpConfig.getPort());
			while (true) {
				
				MainApplication myApp = new MainApplication(serverSocket.accept());		
				
				System.out.println("Connecton opened. (" + sdf.format(new Date()) + ")");
				
				
				thread = new Thread(myApp);
				thread.start();
				
				
				
			}
		
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if (serverSocket!=null && !serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
	}

	public void run() {
		// we manage our particular client connection
		BufferedReader in = null;
		PrintWriter out = null;
		BufferedOutputStream dataOut = null;
		String fileRequested = null;
		
		try {
			// we read characters from the client via input stream on the socket
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// we get character output stream to client (for headers)
			out = new PrintWriter(socket.getOutputStream());
			// get binary output stream to client (for requested data)
			dataOut = new BufferedOutputStream(socket.getOutputStream());
			
			HttpGYRequest request = new HttpGYRequest(socket);
			
			if (request.isNormal()) {
				
			
			
			
			String method = request.getMethod();
			fileRequested = request.getUri();
			System.out.println(request.toString());
			
			if (!method.equals("GET")  &&  !method.equals("HEAD")) {
				if (verbose) {
					System.out.println("501 Not Implemented : " + method + " method.");
				}
				
				// we return the not supported file to the client
				File file = new File(WEB_ROOT, METHOD_NOT_SUPPORTED);
				int fileLength = (int) file.length();
				String contentMimeType = "text/html";
				//read content to return to client
				byte[] fileData = readFileData(file, fileLength);
					
				// we send HTTP Headers with data to client
				out.println("HTTP/1.1 501 Not Implemented");
				out.println("Server: Java HTTP Server from SSaurel : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + contentMimeType);
				out.println("Content-length: " + fileLength);
				out.println(); // blank line between headers and content, very important !
				out.flush(); // flush character output stream buffer
				// file
				dataOut.write(fileData, 0, fileLength);
				dataOut.flush();
				
			} else {
				// GET or HEAD method
				if (fileRequested.endsWith("/")) {
					fileRequested += DEFAULT_FILE;
				}
				
				File file = new File(WEB_ROOT, fileRequested);
				int fileLength = (int) file.length();
				String content = getContentType(fileRequested);
				
				if (method.equals("GET")) { // GET method so we return content
					byte[] fileData = readFileData(file, fileLength);
					
					// send HTTP Headers
					out.println("HTTP/1.1 200 OK");
					out.println("Server: JAVA HTTP Server from GYServer : v 1.0");
					out.println("Date: " + new Date());
					out.println("Content-type: " + content);
					out.println("Content-length: " + fileLength);
					out.println(); // blank line between headers and content, very important !
					out.flush(); // flush character output stream buffer
					
					dataOut.write(fileData, 0, fileLength);
					dataOut.flush();
				}
				
				if (verbose) {
					System.out.println("File " + fileRequested + " of type " + content + " returned");
				}
				
			}
			
			
			}// Request isNormal
		} catch (FileNotFoundException fnfe) {
			try {
				fileNotFound(out, dataOut, fileRequested);
			} catch (IOException ioe) {
				System.err.println("Error with file not found exception : " + ioe.getMessage());
			}
			
		} catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				in.close();
				out.close();
				dataOut.close();
				socket.close(); // we close socket connection
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			} 
			
			if (verbose) {
				System.out.println("Connection closed.\n");
			}
		}
		
		
	}
	
	private byte[] readFileData(File file, int fileLength) throws IOException {
		FileInputStream fileIn = null;
		byte[] fileData = new byte[fileLength];
		
		try {
			fileIn = new FileInputStream(file);
			fileIn.read(fileData);
		} finally {
			if (fileIn != null) 
				fileIn.close();
		}
		
		return fileData;
	}
	
	// return supported MIME Types
	private String getContentType(String fileRequested) {
		if (fileRequested.endsWith(".htm")  ||  fileRequested.endsWith(".html"))
			return "text/html";
		else
			return "text/plain";
	}
	
	private void fileNotFound(PrintWriter out, OutputStream dataOut, String fileRequested) throws IOException {
		File file = new File(WEB_ROOT, FILE_NOT_FOUND);
		int fileLength = (int) file.length();
		String content = "text/html";
		byte[] fileData = readFileData(file, fileLength);
		
		out.println("HTTP/1.1 404 File Not Found");
		out.println("Server: Java HTTP Server from SSaurel : 1.0");
		out.println("Date: " + new Date());
		out.println("Content-type: " + content);
		out.println("Content-length: " + fileLength);
		out.println(); // blank line between headers and content, very important !
		out.flush(); // flush character output stream buffer
		
		dataOut.write(fileData, 0, fileLength);
		dataOut.flush();
		
		if (verbose) {
			System.out.println("File " + fileRequested + " not found");
		}
	}

}
*/
