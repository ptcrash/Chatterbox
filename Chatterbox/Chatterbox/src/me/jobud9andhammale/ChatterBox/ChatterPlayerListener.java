package me.jobud9andhammale.ChatterBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;

public class ChatterPlayerListener extends PlayerListener {
	public final main plugin;
	public ChatterPlayerListener(main plugin) {
	  this.plugin = plugin;
	}
	public void onPlayerChat(PlayerChatEvent e){
		//String message = e.getMessage();
		//String p = e.getPlayer().getName();
//		try {
//			insertMessage(message, p);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		}
	}
	public void insertMessage(String message, String sender) throws ClassNotFoundException, SQLException{
		  Class.forName("com.mysql.jdbc.Driver");
	      Connection connection = null;
		  connection = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/chatterbox", "hammale", "al3xander");
	      Statement statement = null;
	      statement = connection.createStatement();
	      String sql = ("INSERT INTO chatter (`sender`, `message`, `console`, `read`) VALUES ('" + sender + "','"+ message +"','0', '0') ");
		  statement.executeUpdate(sql);
		  connection.close();		
		}
}