package me.ShermansWorld.CustomServerTime;

import me.ShermansWorld.CustomServerTime.config.Config;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpigotExpansion extends PlaceholderExpansion {

	/**
	 * Allows for integration with PlaceholerAPI (PAPI).
	 * Registers a new placeholder called "%customservertime_date%".
	 */

	public String getAuthor() {
		return "ShermansWorld";
	}

	public String getIdentifier() {
		return "CustomServerTime";
	}

	public String getVersion() {
		return Bukkit.getPluginManager().getPlugin("CustomServerTime").getDescription().getVersion();
	}

	public boolean canRegister() {
		return true;
	}

	public boolean persist() {
		return true;
	}

	public String onPlaceholderRequest(Player p, String params) {
		if (p == null)
			return "error";
		if (params.equals("date"))
			return Config.date.generateDate();
		return "error";
	}
}
