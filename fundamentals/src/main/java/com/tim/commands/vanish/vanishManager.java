package com.tim.commands.vanish;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;

public class vanishManager {
    List<Player> isVanished = new ArrayList<>();

    public void removePlayer(Player p){
        isVanished.remove(p);
    }

    public void addPlayer(Player p){
        isVanished.add(p);
    }

    public boolean playerVanished(Player p){
        return isVanished.contains(p);
    }
}
