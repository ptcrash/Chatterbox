package me.JnH.ChatterBox;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class CommandServer implements Runnable {

	public final Main plugin;

	public CommandServer(Main plugin) {
		this.plugin = plugin;

	}
	public void run() {
		int port = plugin.config.getInt("webserver.apiport");
		try {
			plugin.toConsole("Jarserver running on port "+port, 1);
	    }
	    catch (Exception e) {
	    	plugin.toConsole("Cannot Bind to port: "+port+"" + e.getMessage(),2);
	    	return;
	    }
		
		try{
			Socket socket = new Socket("localhost", port);
			BufferedReader read = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = read.readLine();
			while(line != null){
				if(line.startsWith("$")){
					String msg = line.substring(1);
					plugin.toConsole(msg,1);
			     }
			line = read.readLine();
			}
		}
		catch(Exception e){
			plugin.toConsole("Error: "+e.getMessage(), 1);
		}
	}
}