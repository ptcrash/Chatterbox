package me.jobud9andhammale.ChatterBox;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server extends Thread {

	public server(int listen_port, webserver_starter to_send_message_to) {
    message_to = to_send_message_to;
    port = listen_port;
    this.start();
  }

  private void s(String s2) { //an alias to avoid typing so much!
    message_to.send_message_to_window(s2);
  }

  private webserver_starter message_to; //the starter class, needed for gui
  private int port; //port we are going to listen to

//this is a overridden method from the Thread class we extended from
  public void run() {
    //we are now inside our own thread separated from the gui.
    ServerSocket serversocket = null;
    //Pay attention, this is where things starts to cook!
    try {
      //print/send message to the guiwindow
      s("Trying to bind to localhost on port " + Integer.toString(port) + "...");
      //make a ServerSocket and bind it to given port,
      serversocket = new ServerSocket(port);
    }
    catch (Exception e) { //catch any errors and print errors to gui
      s("\nFatal Error:" + e.getMessage());
      return;
    }
    s("OK!\n");
    //go in a infinite loop, wait for connections, process request, send response
    while (true) {
      s("\nReady, Waiting for requests...\n");
      try {
        //this call waits/blocks until someone connects to the port we
        //are listening to
        Socket connectionsocket = serversocket.accept();
        //figure out what ipaddress the client commes from, just for show!
        InetAddress client = connectionsocket.getInetAddress();
        //and print it to gui
        s(client.getHostName() + " connected to server.\n");
        //Read the http request from the client from the socket interface
        //into a buffer.
        BufferedReader input =
            new BufferedReader(new InputStreamReader(connectionsocket.
            getInputStream()));
        //Prepare a outputstream from us to the client,
        //this will be used sending back our response
        //(header + requested file) to the client.
        DataOutputStream output =
            new DataOutputStream(connectionsocket.getOutputStream());

//as the name suggest this method handles the http request, see further down.
//abstraction rules
        http_handler(input, output);
      }
      catch (Exception e) { //catch any errors, and print them
        s("\nError:" + e.getMessage());
      }

    } //go back in loop, wait for next request
  }

//our implementation of the hypertext transfer protocol
//its very basic and stripped down
  private void http_handler(BufferedReader input, DataOutputStream output) {
    int method = 0; //1 get, 2 head, 0 not supported
    new String();
    String path = new String(); //the various things, what http v, what path,
    new String();
    new String();
    try {
      //This is the two types of request we can handle
      //GET /index.html HTTP/1.0
      //HEAD /index.html HTTP/1.0
      String tmp = input.readLine(); //read from the stream
      String tmp2 = new String(tmp);
      tmp.toUpperCase(); //convert it to uppercase
      if (tmp.startsWith("GET")) { //compare it is it GET
        method = 1;
      } //if we set it to method 1
      if (tmp.startsWith("HEAD")) { //same here is it HEAD
        method = 2;
      } //set method to 2

      if (method == 0) { // not supported
        try {
          output.writeBytes(construct_http_header(501, 0));
          output.close();
          return;
        }
        catch (Exception e3) { //if some error happened catch it
          s("error:" + e3.getMessage());
        } //and display error
      }
      //}

      //tmp contains "GET /index.html HTTP/1.0 ......."
      //find first space
      //find next space
      //copy whats between minus slash, then you get "index.html"
      //it's a bit of dirty code, but bear with me...
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
      path = tmp2.substring(start + 2, end); //fill in the path
    }
    catch (Exception e) {
      s("errorr" + e.getMessage());
    } //catch any exception

    //path do now have the filename to what to the file it wants to open
    s("\nClient requested:" + new File(path).getAbsolutePath() + "\n");
    FileInputStream requestedfile = null;

    try {
      requestedfile = new FileInputStream(path);
    }
    catch (Exception e) {
      try {
        //if you could not open the file send a 404
        output.writeBytes(construct_http_header(404, 0));
        //close the stream
        output.close();
      }
      catch (Exception e2) {}
      ;
      s("error" + e.getMessage());
    } //print error to gui

    //happy day scenario
    try {
      //find out what the filename ends with,
      //so you can construct a the right content type
      if (path.endsWith(".zip") || path.endsWith(".exe")
          || path.endsWith(".tar")) {
      }
      if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
      }
      if (path.endsWith(".gif")) {
      }
      output.writeBytes(construct_http_header(200, 5));

      //if it was a HEAD request, we don't print any BODY
      if (method == 1) { //1 is GET 2 is head and skips the body
        while (true) {
          //read the file from filestream, and print out through the
          //client-outputstream on a byte per byte base.
          int b = requestedfile.read();
          if (b == -1) {
            break; //end of file
          }
          output.write(b);
        }
        
      }
//clean up the files, close open handles
      output.close();
      requestedfile.close();
    }

    catch (Exception e) {}

  }

  //this method makes the HTTP header for the response
  //the headers job is to tell the browser the result of the request
  //among if it was successful or not.
  private String construct_http_header(int return_code, int file_type) {
    String s = "HTTP/1.0 ";
    //you probably have seen these if you have been surfing the web a while
    switch (return_code) {
      case 200:
        s = s + "200 OK";
        break;
      case 400:
        s = s + "400 Bad Request";
        break;
      case 403:
        s = s + "403 Forbidden";
        break;
      case 404:
        s = s + "404 Not Found";
        break;
      case 500:
        s = s + "500 Internal Server Error";
        break;
      case 501:
        s = s + "501 Not Implemented";
        break;
    }

    s = s + "\r\n"; //other header fields,
    s = s + "Connection: close\r\n"; //we can't handle persistent connections
    s = s + "Server: Chatterbox for Minecraft\r\n"; //server name

    //Construct the right Content-Type for the header.
    //This is so the browser knows what to do with the
    //file, you may know the browser dosen't look on the file
    //extension, it is the servers job to let the browser know
    //what kind of file is being transmitted. You may have experienced
    //if the server is miss configured it may result in
    //pictures displayed as text!
    switch (file_type) {
      //plenty of types for you to fill in
      case 0:
        break;
      case 1:
        s = s + "Content-Type: image/jpeg\r\n";
        break;
      case 2:
        s = s + "Content-Type: image/gif\r\n";
      case 3:
        s = s + "Content-Type: application/x-zip-compressed\r\n";
      default:
        s = s + "Content-Type: text/html\r\n";
        break;
    }

    ////so on and so on......
    s = s + "\r\n"; //this marks the end of the httpheader
    //and the start of the body
    //ok return our newly created header!
    return s;
  }

}