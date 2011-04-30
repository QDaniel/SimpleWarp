
package com.cruzbishop.simplewarp.commands;

import com.cruzbishop.simplewarp.SimpleWarp;
import com.cruzbishop.simplewarp.Warp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    private final SimpleWarp plugin;

    public WarpCommand(SimpleWarp plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (SimpleWarp.usePermissions) {
            if (!SimpleWarp.permissionHandler.has((Player) sender, "cruzbishop.simplewarp.warp")) {
                sender.sendMessage(ChatColor.RED + "You do not have the rights to use that command");
                return true;
            }
        }
        
        if (args.length < 1) {
            return false;
        }

        String name = args[0];

        Warp warp = plugin.getDatabase().find(Warp.class).where().ieq("name", name).findUnique();

        if (warp == null) {
            sender.sendMessage(ChatColor.RED + "That warp point doesn't look right");
        } else {
            ((Player)sender).teleport(warp.getLocation());
        }

        return true;
    }
}
