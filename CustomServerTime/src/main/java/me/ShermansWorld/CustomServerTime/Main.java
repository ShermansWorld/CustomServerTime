package me.ShermansWorld.CustomServerTime;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static Main instance = null;

	PluginManager pm = Bukkit.getPluginManager();

	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		Timer.startTimer();
		new TimeCommands(this);
		new CustomServerTimeCommands(this);
		new SpigotExpansion().register();
	}
}
