package net.vtorganisation.ranking;

import net.vtorganisation.ranking.config.ConfigFile;
import net.vtorganisation.ranking.config.ConfigHandler;
import net.vtorganisation.ranking.config.FactionDataFile;
import net.vtorganisation.ranking.integration.FactionsManager;
import net.vtorganisation.ranking.integration.FactionsUUID;
import net.vtorganisation.ranking.manager.PluginsManager;
import net.vtorganisation.ranking.manager.RankingManager;
import net.vtorganisation.ranking.task.AutoSaves;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends JavaPlugin {

    private static Ranking instance;
    private ConfigFile configFile;
    private FactionDataFile factionDataFile;
    private List<FactionObject> factions;
    private FactionsManager factionManager;

    private RankingManager rankingManager;

    @Override
    public void onEnable() {
        instance = this;
        long l = System.currentTimeMillis();

        this.configFile = new ConfigFile();
        this.factionDataFile = new FactionDataFile();
        this.factions = new ArrayList<>();

        this.rankingManager = new RankingManager();

        ConfigHandler.register();

        if (!setupFactionsManager()) {
            Bukkit.getPluginManager().disablePlugin(this);
            System.out.println("[vFactionRanking-Lite] §cFactionsuuid or SaberFactions not found! Disabling vFactionRanking-Lite!");
            return;
        }
        this.factionDataFile.loadRanking();

    	PluginsManager.register();

        new AutoSaves().runTaskTimerAsynchronously(this, 20L * ConfigHandler.backupInterval, 20L * ConfigHandler.backupInterval);

    	enabled(l);
    }

    @Override
    public void onDisable(){
        this.factionDataFile.saveFactions();
    }

    private boolean setupFactionsManager() {
        if (Bukkit.getPluginManager().getPlugin("Factions") != null) {
            this.factionManager = new FactionsUUID();
            return true;
        }
        return false;
    }

    private void enabled(long time) {
        System.out.println("[vFactionRanking-Lite] ===============================");
        System.out.println("[vFactionRanking-Lite] Name: " + getDescription().getName());
        System.out.println("[vFactionRanking-Lite] Version: " + getDescription().getVersion());
        System.out.println("[vFactionRanking-Lite] Author: " + getDescription().getAuthors());
        System.out.println("[vFactionRanking-Lite] Loaded " + this.factions.size() + " Factions");
        System.out.println("[vFactionRanking-Lite] Enable Done (Took " + (System.currentTimeMillis() - time) + "ms)");
        System.out.println("[vFactionRanking-Lite] ==============================");
    }

    public static Ranking getInstance() {
        return instance;
    }

    public ConfigFile getConfigFile(){
        return this.configFile;
    }

    public FactionDataFile getFactionDataFile(){
        return this.factionDataFile;
    }

    public List<FactionObject> getFactions(){
        return this.factions;
    }

    public FactionsManager getFactionManager() {
        return this.factionManager;
    }

    public RankingManager getRankingManager(){
        return this.rankingManager;
    }

}