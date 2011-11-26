package me.jobud9andhammale.ChatterBox;

//declare a class wich inherit JFrame
public class webserver_starter {
public static ChatterConfig configuration = new ChatterConfig();
static Integer listen_port = null;
private ChatterConfig plugin;
public webserver_starter(ChatterConfig instance){
	this.plugin = instance;
}
//basic class constructor
public webserver_starter() {
  try {
    jbInit();
  }
  catch (Exception e) {
    e.printStackTrace();
  }
}

//set up the user interface
private void jbInit() throws Exception {
  int listen_port = plugin.getInt("port");
  new server(listen_port, this);
}

//this is a method to get messages from the actual
//server to the window
public void send_message_to_window(String s) {
  System.out.println(s);
}
}

