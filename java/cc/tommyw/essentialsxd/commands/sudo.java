package cc.tommyw.essentialsxd.commands;

import cc.tommyw.essentialsxd.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class sudo extends BukkitCommand {
    public sudo() {
        super("sudo");
        this.description = "Sudo other players";
        this.usageMessage = "/sudo";
        this.setPermission("essentialsxd.sudo");
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        if(args.length < 3) {
            MessageUtils.playerError("Invalid usage! Usage: " + ChatColor.GREEN + "/sudo [player] [normal/op] [command]", (Player) sender);
            return true;
        }

        if(args[1].equalsIgnoreCase("op")) {
            if(Bukkit.getPlayerExact(args[0]) != null) {
                StringBuilder say = new StringBuilder();
                Arrays.stream(args).filter(a -> a != args[0] && a != args[1]).forEach(a -> say.append(a + " "));
                Bukkit.getPlayerExact(args[0]).setOp(true);
                Bukkit.getPlayerExact(args[0]).chat(say.toString());
                Bukkit.getPlayerExact(args[0]).setOp(false);
            }
        } else if(args[1].equalsIgnoreCase("normal")) {
            if(Bukkit.getPlayerExact(args[0]) != null) {
                StringBuilder say = new StringBuilder();
                Arrays.stream(args).filter(a -> a != args[0] && a != args[1]).forEach(a -> say.append(a + " "));
                Bukkit.getPlayerExact(args[0]).chat(say.toString());
            }
        } else {
            MessageUtils.playerError("You selected an invalid mode! Please use OP/Normal", (Player) sender);
        }

        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);

        return true;
    }
}
