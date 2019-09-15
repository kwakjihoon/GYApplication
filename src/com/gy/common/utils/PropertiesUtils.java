package com.gy.common.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {

	public static Properties readProperties(String propertiesPath) {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(propertiesPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
}
