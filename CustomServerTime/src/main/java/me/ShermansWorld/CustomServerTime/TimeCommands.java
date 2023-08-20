package me.ShermansWorld.CustomServerTime;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimeCommands implements CommandExecutor {
	
  public TimeCommands(Main plugin) {
    plugin.getCommand("date").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player p = (Player)sender;
    p.sendMessage(ChatColor.GREEN + "[CustomServerTime] The date is: " + ChatColor.YELLOW + Timer.generateDate());
    return true;
  }
}
