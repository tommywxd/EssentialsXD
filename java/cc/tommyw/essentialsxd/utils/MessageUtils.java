package cc.tommyw.essentialsxd.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {
    public static void logError(String message) {
        System.out.println(ChatColor.DARK_RED + "Critical Error! " + ChatColor.RED + message);
    }

    public static void playerError(String message, Player player) {
        player.sendMessage(ChatColor.RED + message);
    }

    public static void successMessage(String message, Player player) {
        player.sendMessage(ChatColor.GREEN + message);
    }
}
