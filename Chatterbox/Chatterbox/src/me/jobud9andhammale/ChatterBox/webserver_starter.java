package me.jobud9andhammale.ChatterBox;

public class webserver_starter {
private main plugin;

public webserver_starter(main instance){
	this.plugin = instance;
}
public webserver_starter() {
  try {
    jbInit();
  }
  catch (Exception e) {
    e.printStackTrace();
  }
}

private void jbInit() throws Exception {
  int listen_port = 80;
  new server(listen_port, this);
}

public void send_message_to_window(String s) {
  System.out.println(s);
}
}

