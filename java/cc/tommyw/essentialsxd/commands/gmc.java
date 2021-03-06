package cc.tommyw.essentialsxd.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class gmc extends BukkitCommand {
    public gmc() {
        super("gmc");
        this.description = "Change your gamemode to creative.";
        this.usageMessage = "/gmc";
        this.setPermission("essentialsxd.gmc");
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(args.length == 1) {
            Player found = Bukkit.getPlayer(args[0]);
            if(found == null) {
                sender.sendMessage(ChatColor.RED + "Could not find a player with the username " + ChatColor.AQUA + args[0] + ChatColor.RED + ".");
                return true;
            } else {
                sender.sendMessage(ChatColor.BLUE + "Set " + ChatColor.AQUA + args[0] + ChatColor.BLUE+ "'s gamemode to " + ChatColor.AQUA + "CREATIVE" + ChatColor.BLUE + ".");
                found.setGameMode(GameMode.CREATIVE);
                
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "Set your gamemode to " + ChatColor.AQUA + "CREATIVE" + ChatColor.BLUE + ".");
            ((Player) sender).setGameMode(GameMode.CREATIVE);
        }
        return true;
    }
}
