package com.tim.commands.motdchanger;

import com.tim.main;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class motdFileManager {

    final private Plugin plugin = main.getPlugin(main.class);
    public FileConfiguration motdconfig;
    public File motdfile;

    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        motdfile = new File(plugin.getDataFolder(), "motds.yml");

        if(!motdfile.exists()){
            try {
                motdfile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage("-- The motds.yml file has been created! --");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("-- Could not create the motds.yml file! --");
            }
        }

        motdconfig = YamlConfiguration.loadConfiguration(motdfile);
    }

    public FileConfiguration getconf(){
        return motdconfig;
    }

    public void saveConfig(){
        try {
            motdconfig.save(motdfile);
            Bukkit.getServer().getConsoleSender().sendMessage("-- The motds.yml file has been saved! --");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage("-- Could not save the motds.yml file! --");
        }
    }

    public void reloadTpas(){
        motdconfig = YamlConfiguration.loadConfiguration(motdfile);
        Bukkit.getServer().getConsoleSender().sendMessage("-- The motds.yml file has been reloaded! --");
    }
}
