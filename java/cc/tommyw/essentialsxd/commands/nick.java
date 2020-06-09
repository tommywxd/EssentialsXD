package cc.tommyw.essentialsxd.commands;

import cc.tommyw.essentialsxd.utils.MessageUtils;
import cc.tommyw.essentialsxd.utils.NameUtils;
import com.avaje.ebeaninternal.server.core.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static cc.tommyw.essentialsxd.utils.UsefulUtils.getHTML;
import static cc.tommyw.essentialsxd.utils.UsefulUtils.getRandomInteger;

public class nick extends BukkitCommand {
    public nick() {
        super("nick");
        this.description = "Nickname yourself or others.";
        this.usageMessage = "/nick";
        this.setPermission("essentialsxd.nick");
        this.setAliases(new ArrayList<String>());
    }
    public String getRandomName() {
        String randword = "";
        try {
            randword = getHTML("https://random-word-api.herokuapp.com/word?number=1").replace("[\"", "").replace("\"]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fullnick = randword + getRandomInteger(999, 1);
        if (fullnick.length() > 16) {
            return getRandomName();
        }
        return fullnick;
    }
    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
                boolean mc18 = Bukkit.getVersion().contains("1.8");
                boolean mc17 = Bukkit.getVersion().contains("1.7");

                System.out.println(mc18);
                System.out.println(mc17);

                if (args.length == 0) {
                    String fullnick = getRandomName();

                    NameUtils.changeName(fullnick, (Player) sender);
                    ((Player) sender).setDisplayName(fullnick);
                    sender.sendMessage(ChatColor.BLUE + "Set your nick to " + ChatColor.AQUA + fullnick + ChatColor.BLUE + ".");
                    return true;
                }
                if (args.length == 2) {
                    boolean isFound = false;
                    Player selected = null;
                    for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if(player.getDisplayName().equalsIgnoreCase(args[0])) {
                            isFound = true;
                            selected = player;
                        }
                    }
                    selected = Bukkit.getPlayer(args[0]);
                    if (selected != null) {
                        if (args[1].length() > 16) {
                            MessageUtils.playerError("Nick should be within 16 characters.", (Player) sender);
                            return true;
                        }
                        String username = args[1];
                        if (username.equalsIgnoreCase("off")) {
                            NameUtils.changeName(selected.getName(), selected);
                            selected.setDisplayName(selected.getName());
                            sender.sendMessage(ChatColor.BLUE + "Reset their nick to " + ChatColor.AQUA + selected.getName() + ChatColor.BLUE + ".");
                            selected.sendMessage(ChatColor.BLUE + "Reset your nick to " + ChatColor.AQUA + selected.getName() + ChatColor.BLUE + ".");
                            return true;
                        }
                        if (Bukkit.getPlayerExact(username) != null) {
                            MessageUtils.playerError("That username is already taken.", (Player) sender);
                            return true;
                        }
                        boolean inuse = false;
                        for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                            if(player.getDisplayName().equalsIgnoreCase(username)) {
                                inuse = true;
                            }
                        }

                        if(inuse) {
                            MessageUtils.playerError("This nickname is already taken.", (Player) sender);
                            return true;
                        }

                        NameUtils.changeName(username, selected);
                        selected.setDisplayName(username);
                        sender.sendMessage(ChatColor.BLUE + "Set their nick to " + ChatColor.AQUA + username + ChatColor.BLUE + ".");
                        selected.sendMessage(ChatColor.BLUE + "Set your nick to " + ChatColor.AQUA + username + ChatColor.BLUE + ".");
                        return true;
                    } else {
                        sender.sendMessage(ChatColor.RED + "Could not find a player with the username " + ChatColor.AQUA + args[0] + ChatColor.RED + ".");
                        return true;
                    }
                }
                if (args[0].length() > 16) {
                    MessageUtils.playerError("Nick should be within 16 characters.", (Player) sender);
                    return true;
                }
                    if (args[0].equalsIgnoreCase("off")) {
                    NameUtils.changeName(sender.getName(), (Player) sender);
                    ((Player) sender).setDisplayName(((Player) sender).getName());
                    sender.sendMessage(ChatColor.BLUE + "Reset your nick to " + ChatColor.AQUA + sender.getName() + ChatColor.BLUE + ".");
                    return true;
                }
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    MessageUtils.playerError("That username is already taken.", (Player) sender);
                    return true;
                }
                boolean inuse = false;
                for(Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if(player.getDisplayName().equalsIgnoreCase(args[0])) {
                        inuse = true;
                    }
                }

                if(inuse) {
                    MessageUtils.playerError("This nickname is already taken.", (Player) sender);
                    return true;
                }
                NameUtils.changeName(args[0], (Player) sender);
                ((Player) sender).setDisplayName(args[0]);
                sender.sendMessage(ChatColor.BLUE + "Set your nick to " + ChatColor.AQUA + args[0] + ChatColor.BLUE + ".");
                return true;
    }
}
