package com.jobud9.Chatterbox;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class CommandHandler implements CommandExecutor {
	private Plugin plugin;
	CommandHandler(Plugin plugin){
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cb")){
			if(args.length != 0){
				if(args[0].matches("mute")){
					if(sender.hasPermission("cbox.canmute")){
						if(args.length == 1){
							sender.sendMessage(ChatColor.RED+"please enter the name of the player being muted eg:\n /cb mute [Player] ");
						}
						else{
							sender.sendMessage(ChatColor.GREEN+args[1]+" has been notified and Muted!");
						}
					}
					else{
						sender.sendMessage("You do not have permsion!");
					}
				}
				else if(args[0].matches("about")){
					sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getName()+" version "+plugin.getDescription().getVersion());
				}
				else{
					sender.sendMessage(ChatColor.RED+"Command not reconized: "+ args[0]);
				}
				return true;
			}
			else{
				sender.sendMessage(ChatColor.GREEN + plugin.getDescription().getName()+" version "+plugin.getDescription().getVersion());
				return true;
			}
		}
		return false;
	}
}