package me.ShermansWorld.CustomServerTime;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomServerTimeCommands implements CommandExecutor {
  public CustomServerTimeCommands(Main plugin) {
    plugin.getCommand("customservertime").setExecutor(this);
  }
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player p = (Player)sender;
    if (!p.hasPermission("customservertime.reload"))
      p.sendMessage(ChatColor.RED + "[CustomServerTime] You do not have permission to do this"); 
    if (args[0].equalsIgnoreCase("reload")) {
      Main.getInstance().reloadConfig();
      Main.getInstance().saveDefaultConfig();
      Timer.days = Main.getInstance().getConfig().getInt("Day");
      Timer.months = Main.getInstance().getConfig().getInt("Month");
      Timer.years = Main.getInstance().getConfig().getInt("Year");
      p.sendMessage(ChatColor.GOLD + "[CustomServerTime] config.yml reloaded");
    } 
    return false;
  }
}
