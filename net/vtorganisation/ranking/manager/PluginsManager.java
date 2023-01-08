package net.vtorganisation.ranking.manager;

import net.vtorganisation.ranking.Ranking;
import net.vtorganisation.ranking.commands.FPointsCMD;
import net.vtorganisation.ranking.commands.RankingCMD;
import net.vtorganisation.ranking.listeners.RankingChatListener;
import net.vtorganisation.ranking.listeners.RankingListener;
import org.bukkit.Bukkit;

public class PluginsManager {

	public static void register() {
		loadCommands();
		loadListeners();
	}
	
	private static void loadCommands() {
		Ranking.getInstance().getCommand("ranking").setExecutor(new RankingCMD());
		Ranking.getInstance().getCommand("fpoints").setExecutor(new FPointsCMD());
	}

	private static void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new RankingListener(), Ranking.getInstance());
		Bukkit.getPluginManager().registerEvents(new RankingChatListener(), Ranking.getInstance());
	}
}