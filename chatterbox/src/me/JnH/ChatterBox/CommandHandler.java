package me.JnH.ChatterBox;

public class CommandHandler extends Thread{

	public final Main plugin;
	public CommandHandler(Main plugin) {
		this.plugin = plugin;
	}
	
	public void run(){
		plugin.toConsole("hi", 1);
	}
}
