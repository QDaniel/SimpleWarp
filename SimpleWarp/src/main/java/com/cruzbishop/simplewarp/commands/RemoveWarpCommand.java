
package com.cruzbishop.simplewarp.commands;

import com.cruzbishop.simplewarp.SimpleWarp;
import com.cruzbishop.simplewarp.Warp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A class that allows the removal of warps
 * @author Cruz Bishop
 * @version 1.1.0
 */
public class RemoveWarpCommand implements CommandExecutor {
    /**
     * The plugin
     */
    private final SimpleWarp plugin;

    /**
     * Creates a new instance of this command
     * @param plugin The base plugin
     */
    public RemoveWarpCommand(SimpleWarp plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when a command is sent
     * @param sender The sender (AKA player)
     * @param command The command
     * @param label The label
     * @param args The arguments
     * @return Whether this command succeeded or not
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        //No crazy comments in here
        
        if (SimpleWarp.usePermissions) {
            if (!SimpleWarp.permissionHandler.has((Player) sender, "cruzbishop.simplewarp.remove")) {
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
            sender.sendMessage(ChatColor.RED + "That warp doesn't exist anyway!");
            return true;
        }

        plugin.getDatabase().delete(warp);
        
        sender.sendMessage(ChatColor.RED + "Warp \""+warp.getName()+"\" removed!");

        return true;
    }
}
