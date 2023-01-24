package com.tim.commands.tpa;

import com.tim.main;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class requestFileManager {

    final private Plugin plugin = main.getPlugin(main.class);
    public FileConfiguration tpaconfig;
    public File tpafile;

    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        tpafile = new File(plugin.getDataFolder(), "tpas.yml");

        if(!tpafile.exists()){
            try {
                tpafile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage("-- The tpas.yml file has been created! --");
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage("-- Could not create the tpas.yml file! --");
            }
        }

        tpaconfig = YamlConfiguration.loadConfiguration(tpafile);
    }

    public FileConfiguration getconf(){
        return tpaconfig;
    }

    public void saveConfig(){
        try {
            tpaconfig.save(tpafile);
            Bukkit.getServer().getConsoleSender().sendMessage("-- The tpas.yml file has been saved! --");
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender().sendMessage("-- Could not save the tpas.yml file! --");
        }
    }

    public void reloadTpas(){
        tpaconfig = YamlConfiguration.loadConfiguration(tpafile);
        Bukkit.getServer().getConsoleSender().sendMessage("-- The tpa.yml file has been reloaded! --");
    }
}
