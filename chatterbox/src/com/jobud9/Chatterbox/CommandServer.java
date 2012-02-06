package com.jobud9.Chatterbox;

import java.net.ServerSocket;


public class CommandServer implements Runnable {
	public final Main plugin;
	public CommandServer(Main plugin) {
		this.plugin = plugin;

	}
	public void run() {
		int port = plugin.config.getInt("webserver.apiport");
		try {
			ServerSocket serverSocket = new ServerSocket(port);
		} catch (Exception e) {
			System.err.println("Could not listen to JarPort "+port+"!");
		}
	}
}