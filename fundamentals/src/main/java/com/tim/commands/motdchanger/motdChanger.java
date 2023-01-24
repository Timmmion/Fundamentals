package com.tim.commands.motdchanger;

import com.tim.main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class motdChanger implements Listener{

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        FileConfiguration configuration = main.getPlugin(main.class).motdConfManager.getconf();
        List<String> motds = new ArrayList<>();

        if(configuration.contains("motds")){
            motds = configuration.getStringList("motds");
        }else{
            motds.add("This is an example for possible motds");
            configuration.set("motds", motds);
            main.getPlugin(main.class).motdConfManager.saveConfig();
        }

        int min = 0;
        int max = motds.size() - 1;
        int rand = (int)(Math.random()*(max-min)+min);  

        e.setMotd(motds.get(rand));
    }
}
