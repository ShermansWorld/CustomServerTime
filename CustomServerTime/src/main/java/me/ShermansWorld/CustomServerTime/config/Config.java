package me.ShermansWorld.CustomServerTime.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import me.ShermansWorld.CustomServerTime.CustomServerTime;
import me.ShermansWorld.CustomServerTime.DateData;
import me.ShermansWorld.CustomServerTime.date.ServerDate;
import me.ShermansWorld.CustomServerTime.date.ServerMonth;

public class Config {
	
	/**
	 * Pulls values from the configs and stores them in memory for easy access.
	 * Pulls from config.yml and date.yml
	 */
	
	// Config
	private static FileConfiguration config;
	
	// Date Data
	public static DateData dateData;
	
	// Config values
	public static int configVersion;
	public static World world;
	public static ServerDate date;
	public static HashMap<Integer, ServerMonth> months = new HashMap<Integer, ServerMonth>(); // Id, Month
	public static boolean pauseTimerIfNoPlayersOnline;
	public static String dateFormat;
	
	public static void initConfigVals() {
		
		// Get data files
		config = CustomServerTime.getInstance().getConfig();
		dateData = new DateData(CustomServerTime.getInstance());
		
		// Get date values
		int currentDay = dateData.getConfig().getInt("Day");
		int currentMonth = dateData.getConfig().getInt("Month");
		int currentYear = dateData.getConfig().getInt("Year");
		
		// Get config values
		configVersion = config.getInt("config-version");
		world = Bukkit.getWorld(config.getString("World"));
		for (String monthID : config.getConfigurationSection("Months").getKeys(false)) {
			String name = config.getString("Months." + monthID + ".Name");
			int length = config.getInt("Months." + monthID + ".Length");
			months.put(Integer.parseInt(monthID), new ServerMonth(Integer.parseInt(monthID), name, length));
		}
		
		// Generate date object from values
		date = new ServerDate(currentDay, months.get(currentMonth), currentYear);
		
		
		// Settings
		pauseTimerIfNoPlayersOnline = config.getBoolean("PauseTimerIfNoPlayersOnline");
		dateFormat = config.getString("DateFormat");
		
	}
}
