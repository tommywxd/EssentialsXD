package cc.tommyw.essentialsxd.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.ArrayList;

public class essentialsxd extends BukkitCommand {

    public essentialsxd() {
        super("essentialsxd");
        this.description = "EssentialsXD Main Command.";
        this.usageMessage = "/essentialsxd";
        this.setPermission("xdcommands.essentialsxd");
        this.setAliases(new ArrayList<String>());
    }

    @Override
    public boolean execute(CommandSender sender, String alias, String[] args) {
        sender.sendMessage("your mother");
        return true;
    }

}
