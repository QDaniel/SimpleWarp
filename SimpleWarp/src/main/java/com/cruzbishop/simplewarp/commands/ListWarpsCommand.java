
package com.cruzbishop.simplewarp.commands;

import com.cruzbishop.simplewarp.SimpleWarp;
import com.cruzbishop.simplewarp.Warp;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ListWarpsCommand implements CommandExecutor {
    private final SimpleWarp plugin;

    public ListWarpsCommand(SimpleWarp plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (SimpleWarp.usePermissions) {
            if (!SimpleWarp.permissionHandler.has((Player) sender, "cruzbishop.simplewarp.list")) {
                sender.sendMessage(ChatColor.RED + "You do not have the rights to use that command");
                return true;
            }
        }
        
        //Without permissions this is enabled by default
        
        List<Warp> warps = plugin.getDatabase().find(Warp.class).findList();

        if (warps.isEmpty()) {
            sender.sendMessage(ChatColor.RED + "There are no warps");
        } else {
            String result = "";

            for (Warp warp : warps) {
                if (result.length() > 0) {
                    result += ", ";
                }

                result += warp.getName();
            }
            sender.sendMessage("List of "+warps.size()+ " warps:");
            sender.sendMessage(result);
        }

        return true;
    }
}
