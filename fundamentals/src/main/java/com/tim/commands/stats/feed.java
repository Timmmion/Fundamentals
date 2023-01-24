package com.tim.commands.stats;

import com.tim.main;
import java.util.Objects;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class feed implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        Player p = (Player) sender;
        Player pToFeed;

        if(args.length == 0 || args.length >= 2){
            double fed = (int)(20 - p.getFoodLevel());
            double finalFed = fed / 2;
            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Your " + ChatColor.DARK_GRAY + finalFed + ChatColor.GRAY + " empty hungerbar/s were filled up!");
            p.setFoodLevel(20);
        }
        if(args.length == 1){
            try {
                pToFeed = Objects.requireNonNull(Bukkit.getPlayer(args[0]));
                double fed = (int)(20 - pToFeed.getFoodLevel());
                double finalFed = fed / 2;
                p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You've filled up " + ChatColor.DARK_GRAY + pToFeed.getName() + ChatColor.GRAY + " empty hungerbar/s!");
                pToFeed.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Your " + ChatColor.DARK_GRAY + finalFed + ChatColor.GRAY + " empty hungerbar/s were filled up by " + ChatColor.DARK_GRAY + p.getName() + ChatColor.GRAY + "!");
                pToFeed.setFoodLevel(20);
            } catch (Exception e) {
                p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Player not online!");
            }
        }

        return false;
    }

}
