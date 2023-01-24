package com.tim.commands.home;

import com.tim.main;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class homeFileManager {

    final private Plugin plugin = main.getPlugin(main.class);
    public FileConfiguration homeconfig;
    public File homefile;

    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        homefile = new File(plugin.getDataFolder(), "homes.yml");

        if(!homefile.exists()){
            try {
                homefile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage("-- The homes.yml file has been created! --");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("-- Could not create the homes.yml file! --");
            }
        }

        homeconfig = YamlConfiguration.loadConfiguration(homefile);
    }

    public FileConfiguration getconf(){
        return homeconfig;
    }

    public void saveConfig(){
        try {
            homeconfig.save(homefile);
            Bukkit.getServer().getConsoleSender().sendMessage("-- The homes.yml file has been saved! --");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage("-- Could not save the homes.yml file! --");
        }
    }

    public void reloadTpas(){
        homeconfig = YamlConfiguration.loadConfiguration(homefile);
        Bukkit.getServer().getConsoleSender().sendMessage("-- The homes.yml file has been reloaded! --");
    }
}
