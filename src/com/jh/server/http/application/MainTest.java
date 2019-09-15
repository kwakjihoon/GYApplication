package com.jh.server.http.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.PropertyConfigurator;

import com.jh.server.GYServer;
import com.jh.server.Server;

public class MainTest {

	public static void main(String[] args) {

		Server ap = new GYServer();
		ap.start();
		
		
		
	}

}
