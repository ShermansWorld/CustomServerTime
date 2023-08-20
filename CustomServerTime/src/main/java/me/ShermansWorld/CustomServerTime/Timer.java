package me.ShermansWorld.CustomServerTime;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

public class Timer {
	public static int days = Main.getInstance().getConfig().getInt("Day");

	public static int months = Main.getInstance().getConfig().getInt("Month");

	public static int years = Main.getInstance().getConfig().getInt("Year");

	public static String generateDate() {
		String monthString = "NULL";
		switch (months) {
		case 0:
			monthString = Main.getInstance().getConfig().getString("FirstMonth");
			break;
		case 1:
			monthString = Main.getInstance().getConfig().getString("SecondMonth");
			break;
		case 2:
			monthString = Main.getInstance().getConfig().getString("ThirdMonth");
			break;
		case 3:
			monthString = Main.getInstance().getConfig().getString("FourthMonth");
			break;
		case 4:
			monthString = Main.getInstance().getConfig().getString("FifthMonth");
			break;
		case 5:
			monthString = Main.getInstance().getConfig().getString("SixthMonth");
			break;
		case 6:
			monthString = Main.getInstance().getConfig().getString("SeventhMonth");
			break;
		case 7:
			monthString = Main.getInstance().getConfig().getString("EighthMonth");
			break;
		case 8:
			monthString = Main.getInstance().getConfig().getString("NinthMonth");
			break;
		case 9:
			monthString = Main.getInstance().getConfig().getString("TenthMonth");
			break;
		case 10:
			monthString = Main.getInstance().getConfig().getString("EleventhMonth");
			break;
		case 11:
			monthString = Main.getInstance().getConfig().getString("TwelthMonth");
			break;
		default:
			return "ERROR with month";
		}
		String daySuffix = "th";
		if (days + 1 == 2 || days + 1 == 22) {
			daySuffix = "nd";
		} else if (days + 1 == 3 || days + 1 == 23) {
			daySuffix = "rd";
		} else if (days + 1 == 1 || days + 1 == 21 || days + 1 == 31) {
			daySuffix = "st";
		}
		return String.valueOf(monthString) + " " + String.valueOf(days + 1) + daySuffix + ", " + String.valueOf(years);
	}

	public static void startTimer() {
		final World w = Bukkit.getWorld(Main.getInstance().getConfig().getString("World"));
		int[] id = new int[1];
		id[0] = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin) Main.getInstance(),
				new Runnable() {
					public void run() {
						if (w == null || w.getPlayers().size() == 0)
							return;
						int monthDays = 30;
						switch (months) {
						case 0:
							monthDays = Main.getInstance().getConfig().getInt("FirstMonthDays");
							break;
						case 1:
							monthDays = Main.getInstance().getConfig().getInt("SecondMonthDays");
							break;
						case 2:
							monthDays = Main.getInstance().getConfig().getInt("ThirdMonthDays");
							break;
						case 3:
							monthDays = Main.getInstance().getConfig().getInt("FourthMonthDays");
							break;
						case 4:
							monthDays = Main.getInstance().getConfig().getInt("FifthMonthDays");
							break;
						case 5:
							monthDays = Main.getInstance().getConfig().getInt("SixthMonthDays");
							break;
						case 6:
							monthDays = Main.getInstance().getConfig().getInt("SeventhMonthDays");
							break;
						case 7:
							monthDays = Main.getInstance().getConfig().getInt("EighthMonthDays");
							break;
						case 8:
							monthDays = Main.getInstance().getConfig().getInt("NinthMonthDays");
							break;
						case 9:
							monthDays = Main.getInstance().getConfig().getInt("TenthMonthDays");
							break;
						case 10:
							monthDays = Main.getInstance().getConfig().getInt("EleventhMonthDays");
							break;
						case 11:
							monthDays = Main.getInstance().getConfig().getInt("TwelthMonthDays");
							break;
						}
						if (w.getTime() >= 18000L && w.getTime() < 18020L) {
							Timer.days++;
							Main.getInstance().getConfig().set("Day", Integer.valueOf(Timer.days));
							Main.getInstance().saveConfig();
						}
						if (days + 1 >= monthDays) {
							months++;
							days = 0;
							if (months >= 12) {
								months = 0;
								years++;
							}
							Main.getInstance().getConfig().set("Day", Integer.valueOf(days));
							Main.getInstance().getConfig().set("Month", Integer.valueOf(months));
							Main.getInstance().getConfig().set("Year", Integer.valueOf(years));
							Main.getInstance().saveConfig();
						}
					}
				}, 0L, 20L);
	}
}
