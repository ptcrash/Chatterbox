package me.JnH.ChatterBox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class PostServer implements Runnable {

	public final Main plugin;

	public PostServer(Main plugin) {
		this.plugin = plugin;

	}
	public void run() {
		int port = plugin.config.getInt("webserver.jarport");
		ServerSocket serversocket;
		try {
	      serversocket = new ServerSocket(port);
	    }
	    catch (Exception e) {
	      plugin.toConsole("\nFatal Error:" + e.getMessage(),2);
	      return;
	    }
	      try {
	    	  URL oracle = new URL("http://www.oracle.com/");
	          URLConnection yc = oracle.openConnection();
	          BufferedReader in = new BufferedReader(
	          new InputStreamReader(yc.getInputStream()));
	          String inputLine;
	          while ((inputLine = in.readLine()) != null) 
	              System.out.println(inputLine);
	          in.close();
	      	}
	      catch (Exception e) {
	      	plugin.toConsole("\nError:" + e.getMessage(),2);
	   	}
	}	
}
