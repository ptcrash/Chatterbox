package com.jobud9.Chatterbox;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CommandServer implements Runnable {

	public final Main plugin;

	public CommandServer(Main plugin) {
		this.plugin = plugin;

	}
	public void run() {
		int port = plugin.config.getInt("webserver.apiport");
		boolean listening = true;

		try {
			ServerSocket serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(-1);
		}

		while (listening){
			new CommandThread().run();
			//ServerSocket.close();
		}

	}
}