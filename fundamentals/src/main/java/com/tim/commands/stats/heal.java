package com.tim.commands.stats;

import com.tim.main;
import java.util.Objects;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class heal implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        Player p = (Player) sender;
        Player pToHeal;

        if(args.length == 0 || args.length >= 2){
            double healed = (int)(20 - p.getHealth());
            double finalHeal = healed / 2;
            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You were healed by " + ChatColor.DARK_GRAY + finalHeal + ChatColor.GRAY + " heart/s!");
            p.setHealth(20);
        }
        if(args.length == 1){
            try {
                pToHeal = Objects.requireNonNull(Bukkit.getPlayer(args[0]));
                double healed = (int)(20 - pToHeal.getHealth());
                double finalHeal = healed / 2;
                p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You've filled up " + ChatColor.DARK_GRAY + pToHeal.getName() + ChatColor.GRAY + " empty heart/s!");
                pToHeal.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Your " + ChatColor.DARK_GRAY + finalHeal + ChatColor.GRAY + " empty heart/s were filled up by " + ChatColor.DARK_GRAY + p.getName() + ChatColor.GRAY + "!");
                pToHeal.setHealth(20);
            } catch (Exception e) {
                p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Player not online!");
            }

        }

        return false;
    }

}
