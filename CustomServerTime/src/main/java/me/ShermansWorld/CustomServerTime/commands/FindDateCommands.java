package me.ShermansWorld.CustomServerTime.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ShermansWorld.CustomServerTime.CustomServerTime;
import me.ShermansWorld.CustomServerTime.config.Config;
import me.ShermansWorld.CustomServerTime.date.ServerDate;
import me.ShermansWorld.CustomServerTime.date.ServerMonth;
import net.md_5.bungee.api.ChatColor;

public class FindDateCommands implements CommandExecutor {
	/**
	 * Command logic for /date
	 */

	public FindDateCommands(CustomServerTime plugin) {
		plugin.getCommand("finddate").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sendHelpMsg(sender);
			return false;
		} else if (args.length == 1) {
			String epochTimeString = args[0];
			long epochTime;
			try {
				epochTime = Long.parseLong(epochTimeString);
			} catch (NumberFormatException e) {
				sendInvalidInputMsg(sender);
				sendHelpMsg(sender);
				return false;
			}

			// Get time difference in Minecraft days

			long timeDifference = (System.currentTimeMillis() / 1000) - epochTime; // Get time difference
			timeDifference *= 20; // Convert from seconds to ticks
			timeDifference /= 24000; // Convert from ticks to Minecraft days

			// New date variables
			int day = Config.date.getDay();
			ServerMonth month = Config.date.getMonth();
			int year = Config.date.getYear();

			// Get the number of days in a year
			int daysInAYear = 0;
			for (ServerMonth configMonth : Config.months.values()) {
				daysInAYear += configMonth.getLength();
			}
			
			boolean isFutureDate;
			
			if (timeDifference < 0) {
				// Handle future dates
				
				isFutureDate = true;
				timeDifference *= -1;
				
				// Add and record years
				if (timeDifference / daysInAYear > 0) {
					year = (int) (Config.date.getYear() + (timeDifference / daysInAYear));
					timeDifference %= daysInAYear;
				}
				
				// Find month and day
				for (int i = Config.date.getMonth().getID(); i < Config.months.get(Config.months.size()).getID(); i++) {
					ServerMonth configMonth = Config.months.get(i);
					if (timeDifference > configMonth.getLength()) {
						timeDifference -= configMonth.getLength();
					} else {
						day = (int) (Config.date.getDay() + timeDifference);
						month = configMonth;
						if (day > month.getLength()) {
							// Adjust day and month if day exceeds month length
							if (month.getID() == Config.months.get(Config.months.size()).getID()) {
								month = Config.months.get(1);
								year++;
							} else {
								month = Config.months.get(month.getID() - 1);
							}
							day -= configMonth.getLength();
						}
						break;
					}
					// If month not found by the end of the year, start subtracting from the next
					// year
					if (i == Config.months.get(Config.months.size()).getID()) {
						i = 0;
						year++;
					}
				}
				
			} else {
				// Handle past dates
				
				isFutureDate = false;
				// Subtract and record years
				if (timeDifference / daysInAYear > 0) {
					year = (int) (Config.date.getYear() - (timeDifference / daysInAYear));
					timeDifference %= daysInAYear;
				}

				// Find month and day
				for (int i = Config.date.getMonth().getID(); i > 0; i--) {
					ServerMonth configMonth = Config.months.get(i);
					if (timeDifference > configMonth.getLength()) {
						timeDifference -= configMonth.getLength();
					} else {
						day = (int) (Config.date.getDay() - timeDifference);
						month = configMonth;
						if (day <= 0) {
							// Adjust day and month if negative day
							if (month.getID() == 1) {
								month = Config.months.get(Config.months.size());
								year--;
							} else {
								month = Config.months.get(month.getID() - 1);
							}
							day += configMonth.getLength();
						}
						break;
					}
					// If month not found by the end of the year, start subtracting from the next
					// year
					if (i == 1) {
						i = Config.months.get(Config.months.size()).getID() + 1;
						year--;
					}
				}
			}
			
			String dateString = new ServerDate(day, month, year).generateDate();
			sendDateMsg(sender, isFutureDate, dateString);
			return true;
		} else {
			sendHelpMsg(sender);
			return false;
		}
	}

	private void sendHelpMsg(CommandSender sender) {
		if (sender instanceof Player) {
			sender.sendMessage(ChatColor.YELLOW + "[CustomServerTime] command usage: /finddate [epoch time]");
			sender.sendMessage(ChatColor.YELLOW + "Epoch Time Converter: https://www.epochconverter.com/");
		} else {
			sender.sendMessage("[CustomServerTime] command usage: /finddate [epoch time]");
			sender.sendMessage("Epoch Time Converter: https://www.epochconverter.com/");
		}
	}

	private void sendInvalidInputMsg(CommandSender sender) {
		if (sender instanceof Player) {
			sender.sendMessage(ChatColor.RED + "[CustomServerTime] Invalid epoch time, non-numeric");
		} else {
			sender.sendMessage("[CustomServerTime] Invalid epoch time, non-numeric");
		}
	}
	
	private void sendDateMsg(CommandSender sender, boolean isFutureDate, String dateString) {
		if (sender instanceof Player) {
			if (isFutureDate) {
				sender.sendMessage(ChatColor.GREEN + "[CustomServerTime] The date will be: " + ChatColor.YELLOW + dateString);
			} else {
				sender.sendMessage(ChatColor.GREEN + "[CustomServerTime] The date was: " + ChatColor.YELLOW + dateString);
			}
		} else {
			if (isFutureDate) {
				sender.sendMessage("[CustomServerTime] The date will be: " + dateString);
			} else {
				sender.sendMessage("[CustomServerTime] The date was: " + dateString);
			}
		}
	}
}
