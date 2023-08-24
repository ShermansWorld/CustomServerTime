package me.ShermansWorld.CustomServerTime;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.ShermansWorld.CustomServerTime.config.Config;

public class Timer {

	/**
	 * Facilities the passage of time by counting days and updating data. 
	 * Timer is based off Minecraft days.
	 */

	private static BukkitTask dayCounter;

	public static void startTimer() {
		dayCounter = new BukkitRunnable() {
			public void run() {
				// World is invalid, return to prevent error spam
				if (Config.world == null) {
					return;
				}

				// World time is just before midnight, update day
				if (Config.world.getTime() >= 18000L && Config.world.getTime() < 18020L) {
					// Add one to day
					Config.date.setDay(Config.date.getDay() + 1);

					// Set data
					Config.dateData.getConfig().set("Day", Integer.valueOf(Config.date.getDay()));
					Config.dateData.saveConfig();
					
					// if the day is past the last day in the current month
					if (Config.date.getDay() > Config.date.getMonth().getLength()) {
						// If the month is the last month in the year
						if (Config.date.getMonth().equals(Config.months.get(Config.months.size()))) {
							// Reset year, add one year
							Config.date.setMonth(Config.months.get(1));
							Config.date.setYear(Config.date.getYear() + 1);
						} else {
							// Get next month in the year
							Config.date.setMonth(Config.months.get(Config.date.getMonth().getID() + 1));
						}
						// Reset day
						Config.date.setDay(1);

						// Set date
						Config.dateData.getConfig().set("Day", Integer.valueOf(Config.date.getDay()));
						Config.dateData.getConfig().set("Month", Integer.valueOf(Config.date.getMonth().getID()));
						Config.dateData.getConfig().set("Year", Integer.valueOf(Config.date.getYear()));
						Config.dateData.saveConfig();
					}
				}
			}
		}.runTaskTimer(CustomServerTime.getInstance(), 0L, 20L); // Runs instantly, repeats every 1 sec
	}
	
	public static void stopTimer() {
		dayCounter.cancel();
	}
	
}
