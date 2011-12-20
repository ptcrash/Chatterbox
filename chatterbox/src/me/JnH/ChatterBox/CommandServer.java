package me.JnH.ChatterBox;

import java.net.ServerSocket;

public class CommandServer implements Runnable {

	public final Main plugin;

	public CommandServer(Main plugin) {
		this.plugin = plugin;

	}
	public void run() {
		int port = plugin.config.getInt("webserver.jarport");
		ServerSocket sock;
		try {
	      sock = new ServerSocket(port);
	    }
	    catch (Exception e) {
	      plugin.toConsole("Cannot Bind to port:" + e.getMessage(),2);
	      return;
	    }
	      try {
	    	  while(true){
	    		  sock.accept();
	    		  plugin.toConsole(sock.getInetAddress()+" has connected!", 1);
	    	  }
	      	}
	      catch (Exception e) {
	      	plugin.toConsole("\nError:" + e.getMessage(),2);
	   	}
	}	
}
