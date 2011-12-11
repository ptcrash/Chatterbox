package me.JnH.ChatterBox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements Runnable{

	public final main plugin;

	public server(main plugin) {
		this.plugin = plugin;

	}

	public void run() {
		ServerSocket serversocket = null;
		try {
			serversocket = new ServerSocket(plugin.config.getInt("webserver.port"));
		}
		catch (Exception e) {
			plugin.toConsole("Cannot use port "+plugin.config.getInt("webserver.port")+"! It is allready in use",3);
			return;
		}
		while (true) {
			try {
				Socket connectionsocket = serversocket.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(connectionsocket.getInputStream()));
				DataOutputStream output = new DataOutputStream(connectionsocket.getOutputStream());
				String path = new String();
				int file_type = 1;
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
				String rFile = tmp2.substring(start + 2, end);
				path = plugin.PluginDirPath+"html"+File.separator;
				if(rFile.matches("banner.png")){
					path = path + "img"+File.separator+"banner.png";
				}
				else if(rFile.matches("dirt.png")){
					path = path + "img"+File.separator+"dirt.png";
				}
				else if(rFile.matches("grass.png")){
					path = path + "img"+File.separator+"grass.png";
				}
				else if(rFile.matches("stone.png")){
					path = path + "img"+File.separator+"stone.png";
				}
				else{
					path = path + "index.htm";
					file_type = 3;
				}
				FileInputStream requestedfile = null;

				try {
					requestedfile = new FileInputStream(path);
				}
				catch (Exception e) {
					plugin.toConsole("error"+e.getMessage(), 2);
				}

				try {
					//headers
					output.writeBytes("HTTP/1.1");
					output.writeBytes("200 OK");
					output.writeBytes("Connection: close");
					switch (file_type) {
					case 1:
						output.writeBytes("Content-Type: image/png");
						break;
					case 2:
						output.writeBytes("Content-Type: application/x-java-applet");
					case 3:
						output.writeBytes("Content-Type: text/html");
						break;
					}
					output.writeBytes("");
					//body
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
			catch (Exception e) {
				plugin.toConsole("\nError:" + e.getMessage(),1);
			}
		}
	}
}
