package me.ShermansWorld.CustomServerTime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DateData {
	
	/**
	 * Used for interfacing date.yml
	 */
	
	private final String FILENAME = "date.yml";
	
    private CustomServerTime plugin;
    private FileConfiguration dataConfig;
    private File configFile;
    
    public DateData(CustomServerTime plugin) {
        dataConfig = null;
        configFile = null;
        this.plugin = plugin;
        saveDefaultConfig();
    }
    
    public void reloadConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), FILENAME);
        }
        dataConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(configFile);
        final InputStream defaultStream = plugin.getResource(FILENAME);
        if (defaultStream != null) {
            final YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration((Reader)new InputStreamReader(defaultStream));
            dataConfig.setDefaults((Configuration)defaultConfig);
        }
    }
    
    public FileConfiguration getConfig() {
        if (dataConfig == null) {
            reloadConfig();
        }
        return dataConfig;
    }
    
    public void saveConfig() {
        if (dataConfig == null || configFile == null) {
            return;
        }
        try {
            getConfig().save(configFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveDefaultConfig() {
        if (configFile == null) {
            configFile = new File(plugin.getDataFolder(), FILENAME);
        }
        if (!configFile.exists()) {
            plugin.saveResource(FILENAME, false);
        }
    }
}

