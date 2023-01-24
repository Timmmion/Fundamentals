package com.tim.commands.vanish;

import com.tim.main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class vanish implements CommandExecutor{
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        vanishManager vm = main.getPlugin(main.class).vanishManager;
        Player p = (Player) sender;
        
        if(vm.playerVanished(p)){
            vm.removePlayer(p);
            p.showPlayer(main.getPlugin(main.class), p);
            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You're no longer vanished!");
        }else{
            vm.addPlayer(p);
            p.hidePlayer(main.getPlugin(main.class), p);
            p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You're vanished now!");
        }

        return false;
    }
}
