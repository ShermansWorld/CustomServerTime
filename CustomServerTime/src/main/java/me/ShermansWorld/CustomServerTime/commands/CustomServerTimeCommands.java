package me.ShermansWorld.CustomServerTime.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ShermansWorld.CustomServerTime.CustomServerTime;
import me.ShermansWorld.CustomServerTime.config.Config;

public class CustomServerTimeCommands implements CommandExecutor {

	/**
	 * Command logic for /customservertime These are admin utility commands.
	 */

	public CustomServerTimeCommands(CustomServerTime plugin) {
		plugin.getCommand("customservertime").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args[0].equalsIgnoreCase("reload")) {
			Player player;
			if (sender instanceof Player) {
				player = (Player) sender;
				if (!player.hasPermission("customservertime.reload")) {
					player.sendMessage(ChatColor.RED + "[CustomServerTime] You do not have permission to do this");
					return false;
				}
				player.sendMessage(ChatColor.GOLD + "[CustomServerTime] config.yml reloaded");
			} else {
				sender.sendMessage("[CustomServerTime] config.yml reloaded");
			}
			Config.dateData.reloadConfig();
			Config.dateData.saveDefaultConfig();
			CustomServerTime.getInstance().reloadConfig();
			CustomServerTime.getInstance().saveDefaultConfig();
			Config.initConfigVals();
			return true;
		}
		return false;
	}
}
