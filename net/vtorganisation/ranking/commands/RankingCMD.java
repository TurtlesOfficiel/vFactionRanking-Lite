package net.vtorganisation.ranking.commands;

import net.vtorganisation.ranking.Ranking;
import net.vtorganisation.ranking.config.ConfigHandler;
import net.vtorganisation.ranking.utils.NumberFormatted;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RankingCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		sendRankingValues(sender);
		return false;
    }

	private void sendRankingValues(CommandSender sender) {
		HashMap<String, Integer> map = new HashMap<>();
		Ranking.getInstance().getFactions().forEach(factionObject -> map.put(factionObject.getFactionName(), factionObject.getPoints()));

		List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

		ConfigHandler.rankingHeader.forEach(sender::sendMessage);

		int i = 0;
		for(Map.Entry<String, Integer> entry : list.stream().limit(10).collect(Collectors.toList())) {
			String ranking = this.updateColor(i + 1);
			String factionName = entry.getKey();
			int points = entry.getValue();

			sender.sendMessage(ConfigHandler.rankingFormat
					.replace("%ranking%", ranking)
					.replace("%factionName%", factionName)
					.replace("%points%", NumberFormatted.formatted(points)));
			++i;
		}
		ConfigHandler.rankingFooter.forEach(sender::sendMessage);
	}

	private String updateColor(int i) {
		if (i == 1) {
			return ChatColor.GREEN + "➊";
		}
		if (i == 2) {
			return ChatColor.DARK_GREEN + "➋";
		}
		if (i == 3) {
			return ChatColor.GOLD + "➌";
		}
		if(i == 4) {
			return ChatColor.RED + "➍";
		}
		if(i == 5) {
			return ChatColor.DARK_RED + "➎";
		}
		if(i == 6) {
			return ChatColor.DARK_RED + "➏";
		}
		if(i == 7) {
			return ChatColor.DARK_RED + "➐";
		}
		if(i == 8) {
			return ChatColor.DARK_RED + "➑";
		}
		if(i == 9) {
			return ChatColor.DARK_RED + "➒";
		}
		if(i == 10) {
			return ChatColor.DARK_RED + "➓";
		}
		return String.valueOf(ChatColor.DARK_RED) + i;
	}
}