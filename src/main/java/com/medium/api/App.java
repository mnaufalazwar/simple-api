package com.medium.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
	
	public static Properties prop;
	
	public static void main(String[] args) {
		
		System.out.println("Hello World!");
		
		//Load properties dari parameter args[0]
		prop = new Properties();
		
		if(args.length > 0) {
			loadProperties(prop, args[0]);
		} else {
			System.out.println("Parameter Properties empty");
			System.exit(0);
		}
		
		//Config server
		int port = Integer.parseInt(prop.getProperty("server.port", "9001"));
		int maxThreads = Integer.parseInt(prop.getProperty("server.maxthread", "200"));
 		int minThreads = Integer.parseInt(prop.getProperty("server.minthread", "30"));
 		int timeOutMillis = Integer.parseInt(prop.getProperty("server.timeout", "20000"));
		
 		//start server
 		Server server = new Server(port,minThreads,maxThreads, timeOutMillis);
	}
	
	public static void loadProperties(Properties prop, String filename) {
		InputStream input = null;
		try {
			input = new FileInputStream(filename);
			prop.load(input);
		} catch(IOException ex) {
			System.out.println("IOException : " + ex.getMessage());
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch(IOException ex) {
					System.out.println("IOException : " + ex.getMessage());
				}
			}
		}
	}

}

//pembaca bisa explore lebih lagi di http://sparkjava.com/documentation
// Deployment di Server -> Klik kanan project > export > Java > Runnable Jar
// https://qomarullah.medium.com/belajar-java-cara-cepat-membuat-rest-api-a28241dad6ca
