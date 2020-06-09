package cc.tommyw.essentialsxd;

import com.google.common.reflect.ClassPath;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public final class Main extends JavaPlugin {

    void registerAllCommands() {
        ClassPath cp = null;
        try {
            cp = ClassPath.from(getClass().getClassLoader());
        } catch (IOException e) {
            e.printStackTrace();
        }
        cp.getTopLevelClassesRecursive("cc.tommyw.essentialsxd.commands").forEach(classInfo -> {
            Class c = null;
            try {
                c = Class.forName(classInfo.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Object obj = null;
            try {
                assert c != null;
                obj = c.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (obj instanceof Command) {
                Command cmd = (Command) obj;
                registerCommand(cmd, classInfo.getName());
            }
        });
    }

    void registerCommand(Command cmd, String cname) {
        SimpleCommandMap simpleCommandMap = ((CraftServer) this.getServer()).getCommandMap();
        simpleCommandMap.register(cname, cmd);
        System.out.println("Command registered: " + cname);
    }

    @Override
    public void onEnable() {
        registerAllCommands();
    }

    @Override
    public void onDisable() { }

    @Override
    public boolean onCommand(CommandSender sender, Command command,
                             String label, String[] args) {

        return false;
    }

}
