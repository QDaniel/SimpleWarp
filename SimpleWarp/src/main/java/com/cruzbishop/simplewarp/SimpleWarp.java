
package com.cruzbishop.simplewarp;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleWarp extends JavaPlugin {
    public void onDisable() {
    }

    public void onEnable() {
        PluginDescriptionFile desc = getDescription();

        System.out.println(desc.getFullName() + " has been enabled");

        

        setupDatabase();
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
