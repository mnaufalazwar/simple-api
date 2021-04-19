package com.medium.api;

import static spark.Spark.*;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Spark;

public class Server {
	
	public Server(int port, int minThreads, int maxThreads, int timeOutMillis) {
		//apply config and start server
		Spark.port(port);
		threadPool(maxThreads, minThreads, timeOutMillis);
		
		//start routing server path
		Spark.get("/apakabar", (req, res) -> "{\"res\":\"Alhamdulillah\"}");
		
		//apply filter untuk response header json
		apply();
	}
	
	//fungsi untuk apply response header menggunakan json 
		public final static void apply() {
			Filter filter = new Filter() {
				@Override
				public void handle(Request request, Response response) throws Exception {
					response.type("application/json");
				}
			};
			Spark.after(filter);
		}

}
