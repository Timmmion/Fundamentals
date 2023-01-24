package com.tim.commands.tpa;

import com.tim.main;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class tpa implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        FileConfiguration configuration = main.getPlugin(main.class).tpaConfManager.getconf();

        Player pSend = Objects.requireNonNull((Player) sender);
        Player pGet;
        List<String> playerTpaRequests = configuration.getStringList(Objects.requireNonNull(pSend.getName()));

        if(args.length == 1){
            try{
                pGet = Objects.requireNonNull(Bukkit.getPlayer(args[0]));
                if(pGet == pSend){pSend.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You can't teleport to yourself!"); return false;}

                LocalTime time = LocalTime.now(); 
                LocalDate date = LocalDate.now();
                
                String path = time.toSecondOfDay() + "_" + pGet.getUniqueId() + "_" + date;
                playerTpaRequests.add(path);
                configuration.set(Objects.requireNonNull(pSend.getName()), playerTpaRequests);
                configuration.set(path,pSend);
                main.getPlugin(main.class).tpaConfManager.saveConfig();

                pGet.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.DARK_GRAY + pSend.getName() + ChatColor.GRAY + " has sent a TPA-Request!");

                TextComponent msg = new TextComponent(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Accept it with " + ChatColor.DARK_GRAY + "/tpaccept " + pSend.getName() + ChatColor.GRAY + " or click" + ChatColor.DARK_GRAY + " here!");
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaaccept " + pSend.getName()));
                pGet.spigot().sendMessage(msg);

                pSend.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "TPA-Request sent to " + ChatColor.DARK_GRAY + pGet.getName() + ChatColor.GRAY + "!");

            }catch(Exception e ){
                pSend.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Player not online!");
            }
        }else{
            pSend.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Use /tpa [Player]");
        }
        return false;
    }
}
