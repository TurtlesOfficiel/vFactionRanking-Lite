package net.vtorganisation.ranking.config;


import net.vtorganisation.ranking.Ranking;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigFile {

    private final Ranking plugin;
    private File file;
    private YamlConfiguration configuration;

    public ConfigFile() {
        (this.plugin = Ranking.getInstance()).saveDefaultConfig();
        this.file = new File(this.plugin.getDataFolder(), "config.yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public void load() {
        this.file = new File(this.plugin.getDataFolder(), "config.yml");
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }

    public double getDouble(String paramString) {
        if (this.configuration.contains(paramString)) {
            return this.configuration.getDouble(paramString);
        }
        return 0.0D;
    }

    public int getInt(String paramString) {
        if (this.configuration.contains(paramString)) {
            return this.configuration.getInt(paramString);
        }
        return 0;
    }

    public List<Short> getShortList(String paramString){
        if(this.configuration.contains(paramString)){
            return this.configuration.getShortList(paramString);
        }
        return null;
    }

    public boolean getBoolean(String paramString) {
        return (this.configuration.contains(paramString)) && (this.configuration.getBoolean(paramString));
    }

    public String getString(String paramString) {
        if (this.configuration.contains(paramString)) {
            return ChatColor.translateAlternateColorCodes('&', this.configuration.getString(paramString));
        }
        return "String at path: " + paramString + " not found!";
    }

    public List<String> getStringList(String paramString) {
        if (this.configuration.contains(paramString)) {
            List<String> arrayList = new ArrayList<>();
            for (String str : this.configuration.getStringList(paramString)) {
                arrayList.add(ChatColor.translateAlternateColorCodes('&', str));
            }
            return arrayList;
        }
        return Collections.singletonList("String List at path: " + paramString + " not found!");
    }
}
