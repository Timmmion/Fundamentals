package com.tim.commands.home;

import com.tim.main;
import java.util.Objects;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class sethome implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        
        Player p = (Player) sender;
        FileConfiguration configuration = main.getPlugin(main.class).homeConfManager.getconf();
        
        configuration.set(Objects.requireNonNull(p.getName()), p.getLocation());
        p.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Your home has been set!");
        main.getPlugin(main.class).homeConfManager.saveConfig();

        return  false;
    }

}
