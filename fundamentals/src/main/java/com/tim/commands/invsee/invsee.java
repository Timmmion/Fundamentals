package com.tim.commands.invsee;

import com.tim.main;
import java.util.Objects;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class invsee implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        Player p = (Player) sender;

        if(args.length == 1){
            try{
                Player p2 = Objects.requireNonNull(Bukkit.getServer().getPlayer(args[0]));
                if(p2.getUniqueId() == p.getUniqueId()) {
                    p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Press your inventory key to look at it!");
                    return true;
                }
                p.openInventory(Objects.requireNonNull(p2.getInventory()));
            }catch(Exception e){
                p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Player not found!");
            }
        }else{
            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Use /invsee [Player]");
        }
        return false;
    }
}
