package com.jobud9.Chatterbox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer implements Runnable{
	public final Main plugin;
	boolean run = true;
	public FileServer(Main plugin) {
		this.plugin = plugin;
	}

	public void run() {
		int port = plugin.config.getInt("webserver.port");
		ServerSocket serversocket = null;
		try {
			serversocket = new ServerSocket(port);
		}
		catch (Exception e) {
			plugin.toConsole("Cannot bind to port"+port+"! It is allready in use.",3);
			plugin.toConsole("shutting down webserver! please free port "+port+" or change the selected port in the config!", 3);
			run = false;
		}
		while (run) {
			try {
				Socket connectionsocket = serversocket.accept();
				http_handler(new BufferedReader(new InputStreamReader(connectionsocket.getInputStream())), new DataOutputStream(connectionsocket.getOutputStream()));
			}
			catch (Exception e) {
				plugin.toConsole("Error:" + e.getMessage(),2);
				run = false;
			}
		}
	}

	private void http_handler(BufferedReader input, DataOutputStream output) {
		String path = new String();
		try {
			String tmp = input.readLine();
			String tmp2 = new String(tmp);
			tmp.toUpperCase();
			int start = 0;
			int end = 0;
			for (int a = 0; a < tmp2.length(); a++) {
				if (tmp2.charAt(a) == ' ' && start != 0) {
					end = a;
					break;
				}
				if (tmp2.charAt(a) == ' ' && start == 0) {
					start = a;
				}
			}
			path = tmp2.substring(start + 2, end);
		}
		catch (Exception e) {
			plugin.toConsole("Error: "+e.getMessage(),2);
			e.printStackTrace();
		}

		FileInputStream requestedfile = null;

		try {
			if(path.matches("applet.class")|| path.matches("applet$EventHandler.class") || path.matches("dirt.png") || path.matches("stone.png") || path.matches("grass.png")
			|| path.matches("banner.png")||path.matches("grass.png") || path.matches("banner.png") || path.matches("index.html") || path.matches("favicon.ico")){
				requestedfile = new FileInputStream(plugin.PluginDirPath+"html"+File.separator+path);
			}
			else{
				requestedfile = new FileInputStream(plugin.PluginDirPath+"html"+File.separator+"index.htm");
			}

		} catch (FileNotFoundException e) {
			plugin.toConsole("could not find file \""+path+"\" please make sure it is there!", 2);
		}

		try {
			int file_type;
			if(path.matches("dirt.png")){
				file_type = 1;
			}
			else if(path.matches("banner.png")){
				file_type = 1;
			}
			else if(path.matches("grass.png")){
				file_type = 1;
			}
			else if(path.matches("stone.png")){
				file_type = 1;
			}
			else if(path.matches("favicon.ico")){
				file_type = 3;
			}
			else if(path.matches("applet.class")){
				file_type = 2;
			}
			else if(path.matches("applet$EventHandler.class")){
				file_type = 2;
			}
			else{
				file_type = 0;
			}
			output.writeBytes(construct_http_header(file_type));
			while (true) {
				int b = requestedfile.read();
				if (b == -1) {
					break;
				}
				output.write(b);
			}

			output.close();
			requestedfile.close();
		}
		catch (Exception e) {}

	}

	private String construct_http_header(int file_type) {
		String s = "HTTP/1.0 ";
		s = s + "200 OK";
		s = s + "\r\n";
		s = s + "Connection: close\r\n";

		switch (file_type) {
		case 0:
			s = s + "Content-Type: text/html\r\n";
			break;
		case 1:
			s = s + "Content-Type: image/png\r\n";
			break;
		case 2:
			s = s + "Content-Type: application/x-java-applet\r\n";
			break;
		case 3:
			s = s + "Content-Type: image/vnd.microsoft.icon\r\n";
			break;
		default:
			s = s + "Content-Type: text/html\r\n";
			break;
		}

		s = s + "\r\n";
		return s;
	}

}