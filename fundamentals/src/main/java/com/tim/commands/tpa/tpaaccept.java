package com.tim.commands.tpa;

import com.tim.main;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class tpaaccept implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) return true;
        final String PREFIX = main.getPlugin(main.class).prefix;
        FileConfiguration configuration = main.getPlugin(main.class).tpaConfManager.getconf();

        Player pGet = Objects.requireNonNull((Player) sender);
        Player pSend; 

        LocalTime time = LocalTime.now();
        LocalDate date = LocalDate.now();

        if(args.length == 1){
            String name = args[0];
            try{
                for(int i = 0;i < 300; i++){
                    String path = (time.toSecondOfDay() - i) + "_" + pGet.getUniqueId() + "_" + date;
                    if(configuration.contains(path) && Bukkit.getPlayer(args[0]) == configuration.get(path)){

                        pSend = Objects.requireNonNull((Player) configuration.get(path));

                        List<String> playerTpaRequests = configuration.getStringList(Objects.requireNonNull(pSend.getName()));
                        for(int a = 0;a < playerTpaRequests.size(); a++){
                            configuration.set(playerTpaRequests.get(a), null);
                        }

                        configuration.set(Objects.requireNonNull(pSend.getName()), null);
                        configuration.set(path, null);
                        main.getPlugin(main.class).tpaConfManager.saveConfig();

                        pGet.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "TPA-Request accepted!");
                        pSend.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You will be teleported in 5 seconds! Don't move!");

                        final Double x = pSend.getLocation().getX();
                        final Double z = pSend.getLocation().getZ();

                        final Player pSendFinal = pSend;
                        final Player pGetFinal = pGet;

                        BukkitRunnable task = new BukkitRunnable() {
                            @Override
                            public void run(){
                                Double xAfter5Sec = pSendFinal.getLocation().getX();
                                Double zAfter5Sec = pSendFinal.getLocation().getZ();

                                Double distance = Math.sqrt(Math.pow(xAfter5Sec - x, 2) + Math.pow(zAfter5Sec - z, 2));

                                if(distance <= 3){
                                    pSendFinal.teleport(pGetFinal.getLocation());
                                    pGetFinal.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.DARK_GRAY + pSendFinal.getName() + ChatColor.GRAY + " was telported to you!");
                                    pSendFinal.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You were teleported to " + ChatColor.DARK_GRAY + pGetFinal.getName() + ChatColor.GRAY + "!");
                                }else{
                                    pSendFinal.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "You moved and therefore were not teleported!");
                                    pGetFinal.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.DARK_GRAY + pSendFinal.getName() + ChatColor.GRAY +" was not teleported to you because he/she moved!");
                                }
                            }
                        };task.runTaskLater(main.getPlugin(main.class), 100);
                        return true;
                    }
                }
                pGet.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.DARK_GRAY + name + ChatColor.GRAY + " has no open TPA-Requests to you!");
                return true;
            }catch(RuntimeException e){
                e.getStackTrace();
            }
        }else{
            pGet.sendMessage(ChatColor.YELLOW + PREFIX + ChatColor.GRAY + "Use /tpaaccept [Player]");
        }

        return false;
    }
    
}
