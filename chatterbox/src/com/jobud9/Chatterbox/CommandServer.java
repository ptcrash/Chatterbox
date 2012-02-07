package com.jobud9.Chatterbox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;


public class CommandServer implements Runnable {
	public final Main plugin;
	public CommandServer(Main plugin) {
		this.plugin = plugin;

	}
	public void run() {
		int port = plugin.config.getInt("webserver.apiport");
		try {
			ServerSocket Sock = new ServerSocket(port);
			BufferedReader in = new BufferedReader(new InputStreamReader(Sock.accept().getInputStream()));
			DataOutputStream out = new DataOutputStream(Sock.accept().getOutputStream());
			
			if(in.equals("REQUEST Connect")){
				out.writeBytes("CONFIRM Connect\r\n");
				plugin.toConsole("Player Connected", 1);
			}
		} catch (Exception e) {
			System.err.println("Could not listen to JarPort "+port+"!");
		}
	}
}