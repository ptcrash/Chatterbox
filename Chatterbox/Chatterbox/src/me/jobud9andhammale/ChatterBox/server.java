package me.jobud9andhammale.ChatterBox;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class server {

  protected void start() {
    ServerSocket s;

    try {
      // create the main server socket
      s = new ServerSocket(80);
    } catch (Exception e) {
      System.out.println("Cannot bind to port! Port is in use!");
      return;
    }

    for (;;) {
      try {
        Socket remote = s.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(
            remote.getInputStream()));
        PrintWriter out = new PrintWriter(remote.getOutputStream());
        String str = ".";
        while (!str.equals(""))
          str = in.readLine();
        out.println("HTTP/1.0 200 OK");
        out.println("Content-Type: text/html");
        out.println("Server: Bot");
        out.println("");
        try{
        	  FileInputStream fstream = new FileInputStream("textfile.txt");
        	  DataInputStream input = new DataInputStream(fstream);
        	  BufferedReader br = new BufferedReader(new InputStreamReader(input));
        	  String strLine;
        	  while ((strLine = br.readLine()) != null)   {
        	  System.out.println (strLine);
        	  }
        	  input.close();
        	    }catch (Exception e){
        	  System.err.println("Error: " + e.getMessage());
        	  }
        out.println("<H1>Welcome to the Ultra Mini-WebServer</H2>");
        out.flush();
        remote.close();
      } catch (Exception e) {
        System.out.println("Error: " + e);
      }
    }
  }
  public static void main(String args[]) {
    WebServer ws = new WebServer();
    ws.start();
  }
}