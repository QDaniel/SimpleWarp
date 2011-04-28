
package com.cruzbishop.simplewarp;

import com.cruzbishop.simplewarp.commands.AddWarpCommand;
import com.cruzbishop.simplewarp.commands.ListWarpsCommand;
import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleWarp extends JavaPlugin {
    
    public static PermissionHandler permissionHandler;
    
    public void onDisable() {
    }

    public void onEnable() {
        PluginDescriptionFile desc = getDescription();
        
        getCommand("listwarps").setExecutor(new ListWarpsCommand(this));
        getCommand("addwarp").setExecutor(new AddWarpCommand(this));

        System.out.println(desc.getFullName() + " has been enabled");

        setupPermissions();

        setupDatabase();
    }
    
    private void setupPermissions() {
      Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");

      if (permissionHandler == null) {
          if (permissionsPlugin != null) {
              permissionHandler = ((Permissions) permissionsPlugin).getHandler();
          } else {
              System.out.println("Permission system not detected, defaulting to OP");
          }
      }
  }

    private void setupDatabase() {
        try {
            getDatabase().find(Warp.class).findRowCount();
        } catch (PersistenceException ex) {
            System.out.println("Installing database for " + getDescription().getName() + " due to first time usage");
            installDDL();
        }
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(Warp.class);
        return list;
    }
}
