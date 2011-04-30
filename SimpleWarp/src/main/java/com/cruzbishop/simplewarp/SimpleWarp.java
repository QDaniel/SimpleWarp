package com.cruzbishop.simplewarp;

import com.cruzbishop.simplewarp.commands.AddWarpCommand;
import com.cruzbishop.simplewarp.commands.ListWarpsCommand;
import com.cruzbishop.simplewarp.commands.RemoveWarpCommand;
import com.cruzbishop.simplewarp.commands.WarpCommand;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A simple plugin that adds warp functionality
 * This class is the base for everything
 * @author Cruz Bishop
 * @version 1.1.0
 */
public class SimpleWarp extends JavaPlugin {

    /**
     * The permission handler
     */
    public static PermissionHandler permissionHandler;
    /**
     * Whether to use permissions or not
     */
    public static boolean usePermissions = true;

    /**
     * Called when this plugin is disabled.
     */
    public void onDisable() {
        //DO NOTHING
    }

    /**
     * Called when this plugin is enabled
     */
    public void onEnable() {
        PluginDescriptionFile desc = getDescription();

        getCommand("listwarps").setExecutor(new ListWarpsCommand(this));
        getCommand("addwarp").setExecutor(new AddWarpCommand(this));
        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("removewarp").setExecutor(new RemoveWarpCommand(this));

        System.out.println(desc.getFullName() + " has been enabled");

        setupPermissions();

        setupDatabase();
    }

    /**
     * Sets up the permission handler
     */
    private void setupPermissions() {
        Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

        if (permissionHandler == null) {
            if (permissionsPlugin != null) {
                permissionHandler = ((Permissions) permissionsPlugin).getHandler();
            } else {
                System.out.println("WARNING! Permission system not detected, defaulting to OP");
                usePermissions = false;
            }
        }
    }

    /**
     * Sets up the database
     */
    private void setupDatabase() {
        try {
            getDatabase().find(Warp.class).findRowCount();
        } catch (PersistenceException ex) {
            System.out.println("Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
    }

    @Override
    /**
     * Gets the database classes
     */
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Warp.class);
        return list;
    }
}
