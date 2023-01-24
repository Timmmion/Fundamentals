package com.tim.commands.home;

import com.tim.main;
import java.util.Objects;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class home implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;

        final Player p = (Player) sender;
        final FileConfiguration configuration = main.getPlugin(main.class).homeConfManager.getconf();

        if(configuration.contains(Objects.requireNonNull(p.getName()))){

            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You will be teleported in 5 seconds!");
            BukkitRunnable task = new BukkitRunnable() {
                Location loc = configuration.getLocation(Objects.requireNonNull(p.getName()));
                @Override
                public void run(){
                    p.teleport(loc);
                }
            };task.runTaskLater(main.getPlugin(main.class), 100);
            
        }else{
            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Set a home first!");
        }
        
        return false;
    }
}
