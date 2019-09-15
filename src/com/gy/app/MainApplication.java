package com.gy.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.PropertyConfigurator;

import com.gy.server.GYServer;
import com.gy.server.Server;

public class MainApplication {

	public static void main(String[] args) {
		Server ap = new GYServer();
		ap.start();
	}
}
