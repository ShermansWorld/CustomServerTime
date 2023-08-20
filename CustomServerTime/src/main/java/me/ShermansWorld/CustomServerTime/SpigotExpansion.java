package me.ShermansWorld.CustomServerTime;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

public class SpigotExpansion extends PlaceholderExpansion {
  public String getAuthor() {
    return "ShermansWorld";
  }
  
  public String getIdentifier() {
    return "CustomServerTime";
  }
  
  public String getVersion() {
    return "1.0.3";
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
      return Timer.generateDate(); 
    return "error";
  }
}
