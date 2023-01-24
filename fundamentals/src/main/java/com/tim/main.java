package com.tim;

import com.tim.commands.home.home;
import com.tim.commands.home.homeFileManager;
import com.tim.commands.home.sethome;
import com.tim.commands.invsee.invsee;
import com.tim.commands.motdchanger.motdChanger;
import com.tim.commands.motdchanger.motdFileManager;
import com.tim.commands.stats.feed;
import com.tim.commands.stats.heal;
import com.tim.commands.tpa.requestFileManager;
import com.tim.commands.tpa.tpa;
import com.tim.commands.tpa.tpaaccept;
import com.tim.commands.vanish.vanish;
import com.tim.commands.vanish.vanishManager;
import java.util.Objects;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("fundamentals");

  public requestFileManager tpaConfManager;
  public homeFileManager homeConfManager;
  public motdFileManager motdConfManager;
  public vanishManager vanishManager;
  public String prefix;

  @Override
  public void onEnable()
  {
    enableCommands();
    confPrefix();
    createTpaConfigurationManager();
    createHomeConfigurationManager();
    createMotdConfigurationManager();
    createVanishManager();
    getServer().getPluginManager().registerEvents(new motdChanger(), this);
    LOGGER.info("Fundamentals by Timmmion are enabled now!");
  }


  @Override
  public void onDisable()
  {
    saveAllConfigs();
    LOGGER.info("Fundamentals by Timmmion are disabled now!");
  }


  private void enableCommands(){
    Objects.requireNonNull(getCommand("tpa")).setExecutor(new tpa());
    Objects.requireNonNull(getCommand("tpaaccept")).setExecutor(new tpaaccept());
    Objects.requireNonNull(getCommand("home")).setExecutor(new home());
    Objects.requireNonNull(getCommand("sethome")).setExecutor(new sethome());
    Objects.requireNonNull(getCommand("invsee")).setExecutor(new invsee());
    Objects.requireNonNull(getCommand("vanish")).setExecutor(new vanish());
    Objects.requireNonNull(getCommand("heal")).setExecutor(new heal());
    Objects.requireNonNull(getCommand("feed")).setExecutor(new feed());
  }


  private void confPrefix(){
    FileConfiguration config = main.getPlugin(main.class).getConfig();
    if(config.contains("ServerPrefix")){
      prefix = config.getString("ServerPrefix");
      prefix = prefix.concat(" ");
    }else{
      config.set("ServerPrefix", "[Server] ");
      prefix = "[Server] ";
      main.getPlugin(main.class).saveConfig();
    }
  }


  public void createTpaConfigurationManager(){
    tpaConfManager = new requestFileManager();
    tpaConfManager.setup();
    tpaConfManager.saveConfig();
    tpaConfManager.reloadTpas();
  }

  public void createHomeConfigurationManager(){
    homeConfManager = new homeFileManager();
    homeConfManager.setup();
    homeConfManager.saveConfig();
    homeConfManager.reloadTpas();
  }

  public void createMotdConfigurationManager(){
    motdConfManager = new motdFileManager();
    motdConfManager.setup();
    motdConfManager.saveConfig();
    motdConfManager.reloadTpas();
  }

  public void createVanishManager(){
    vanishManager = new vanishManager();
  }


  public void saveAllConfigs(){
    tpaConfManager.saveConfig();
    homeConfManager.saveConfig();
    motdConfManager.saveConfig();
  }
}
