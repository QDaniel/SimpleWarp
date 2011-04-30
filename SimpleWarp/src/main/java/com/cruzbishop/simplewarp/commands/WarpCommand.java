
package com.cruzbishop.simplewarp.commands;

import com.cruzbishop.simplewarp.SimpleWarp;
import com.cruzbishop.simplewarp.Warp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A command that allows warping
 * @author Cruz Bishop
 * @version 1.1.0
 */
public class WarpCommand implements CommandExecutor {
   
    /**
     * The plugin
     */
    private final SimpleWarp plugin;

    /**
     * Creates a new warp command instance
     * @param plugin The base plugin
     */
    public WarpCommand(SimpleWarp plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when a command is sent
     * @param sender The sender (aka player)
     * @param command The command
     * @param label The label
     * @param args The arguments used
     * @return Whether the command succeeded or not
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (SimpleWarp.usePermissions) {
            //OH YES YOU ARE UP TO DATE
            if (!SimpleWarp.permissionHandler.has((Player) sender, "simplewarp.warp")) {
                //...And you're so ****ing mean! I mean, who would not want players to warp?
                //Seriously
                //You're mean
                sender.sendMessage(ChatColor.RED + "You do not have the rights to use that command");
                return true;
            }
        }
        //We can continue
        if (args.length < 1) {
            //Wait, you're warping... NOWHERE? Noob
            return false;
        }

        //I know your name now
        String name = args[0];

        //Where you want to go
        Warp warp = plugin.getDatabase().find(Warp.class).where().ieq("name", name).findUnique();

        if (warp == null) {
            //Stop trying to warp to heaven
            sender.sendMessage(ChatColor.RED + "That warp point doesn't look right");
        } else {
            //BYE! Don't come back!
            ((Player)sender).teleport(warp.getLocation());
        }

        return true;
    }
}
