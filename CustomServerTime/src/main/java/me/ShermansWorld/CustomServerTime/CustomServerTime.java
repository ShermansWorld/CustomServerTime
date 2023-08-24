package me.ShermansWorld.CustomServerTime;

import org.bukkit.plugin.java.JavaPlugin;

import me.ShermansWorld.CustomServerTime.commands.CustomServerTimeCommands;
import me.ShermansWorld.CustomServerTime.commands.DateCommands;
import me.ShermansWorld.CustomServerTime.commands.FindDateCommands;
import me.ShermansWorld.CustomServerTime.config.Config;

public class CustomServerTime extends JavaPlugin {
	
	/**
	 * Main plugin class.
	 */
	
	public static CustomServerTime instance = null;

	public static CustomServerTime getInstance() {
		return instance;
	}

	public void onEnable() {
		
		instance = this;
		
		// Initialize config
		saveDefaultConfig();
		Config.initConfigVals();
		
		// Initialize date timer
		Timer.startTimer();
		
		// Initialize Commands
		new DateCommands(this);
		new CustomServerTimeCommands(this);
		new FindDateCommands(this);
		
		// PAPI support
		new SpigotExpansion().register();
	}
}
