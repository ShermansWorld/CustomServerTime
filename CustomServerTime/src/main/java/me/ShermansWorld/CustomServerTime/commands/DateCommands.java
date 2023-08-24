package me.ShermansWorld.CustomServerTime.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ShermansWorld.CustomServerTime.CustomServerTime;
import me.ShermansWorld.CustomServerTime.config.Config;

public class DateCommands implements CommandExecutor {

	/**
	 * Command logic for /date
	 */

	public DateCommands(CustomServerTime plugin) {
		plugin.getCommand("date").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sendDateMsg(sender);
		return true;
	}

	public static void sendDateMsg(CommandSender sender) {
		if (sender instanceof Player) {
			sender.sendMessage(ChatColor.GREEN + "[CustomServerTime] The date is: " + ChatColor.YELLOW
					+ Config.date.generateDate());
		} else {
			sender.sendMessage("[CustomServerTime] The date is: " + Config.date.generateDate());
		}
	}
}
