
package com.cruzbishop.simplewarp.commands;

import com.cruzbishop.simplewarp.SimpleWarp;
import com.cruzbishop.simplewarp.Warp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddWarpCommand implements CommandExecutor {
    private final SimpleWarp plugin;

    public AddWarpCommand(SimpleWarp plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (SimpleWarp.usePermissions) {
            if (!SimpleWarp.permissionHandler.has((Player) sender, "cruzbishop.simplewarp.add")) {
                sender.sendMessage(ChatColor.RED + "You do not have the rights to use that command");
                return true;
            }
        } else {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "Only ops can use this command!");
                return true;
            }
        }

        if (args.length < 1) {
            return false;
        }

        String name = args[0];

        Warp warp = plugin.getDatabase().find(Warp.class).where().ieq("name", name).findUnique();

        if (warp == null) {
            warp = new Warp();
            warp.setName(name);
        }

        warp.setLocation(((Player)sender).getLocation());

        plugin.getDatabase().save(warp);
        
        sender.sendMessage(ChatColor.RED + "Warp added");

        return true;
    }
}
