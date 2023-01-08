package net.vtorganisation.ranking.commands;

import net.vtorganisation.ranking.FactionObject;
import net.vtorganisation.ranking.Ranking;
import net.vtorganisation.ranking.config.ConfigHandler;
import net.vtorganisation.ranking.utils.NumberFormatted;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FPointsCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!sender.hasPermission("ranking.admin")) {
            sender.sendMessage(ConfigHandler.prefix + ConfigHandler.noPermission);
            return true;
        }
        if(args.length < 2) {
            sender.sendMessage("§cUsage: /" + label + " <add/remove/set/reset> <player> <points>");
            return true;
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("reset")){
                String username = args[1];
                Player target = Bukkit.getPlayer(username);

                if (target == null) {
                    sender.sendMessage(ConfigHandler.prefix + ConfigHandler.playerIsOffline);
                    return true;
                }
                String factionName = Ranking.getInstance().getFactionManager().getFactionTag(target);
                FactionObject factionObject = Ranking.getInstance().getRankingManager().getFaction(factionName);

                if (!Ranking.getInstance().getFactionManager().hasFaction(target) || !Ranking.getInstance().getRankingManager().isExist(factionName)) {
                    sender.sendMessage(ConfigHandler.prefix + ConfigHandler.playerHasNotFaction);
                    return true;
                }
                if (factionObject.getPoints() <= 0) {
                    sender.sendMessage(ConfigHandler.prefix + ConfigHandler.cannotWithdrawMore);
                    return true;
                }
                factionObject.setPoints(0);
                sender.sendMessage(ConfigHandler.prefix + ConfigHandler.pointsResetToFaction.replace("%faction%", factionName));
            }
        }

        if(args.length == 3){
            String subCommands = args[0];

            String username = args[1];
            Player target = Bukkit.getPlayer(username);

            if (target == null) {
                sender.sendMessage(ConfigHandler.prefix + ConfigHandler.playerIsOffline);
                return true;
            }
            String factionName = Ranking.getInstance().getFactionManager().getFactionTag(target);
            FactionObject factionObject = Ranking.getInstance().getRankingManager().getFaction(factionName);

            if (!Ranking.getInstance().getFactionManager().hasFaction(target) || !Ranking.getInstance().getRankingManager().isExist(factionName)) {
                sender.sendMessage(ConfigHandler.prefix + ConfigHandler.playerHasNotFaction);
                return true;
            }
            if (isNumber(args[2])) {
                sender.sendMessage(ConfigHandler.prefix + ConfigHandler.invalidNumber);
                return true;
            }
            long points = Long.parseLong(args[2]);

            switch (subCommands){
                case "add":
                    factionObject.addPoints(points);
                    sender.sendMessage(ConfigHandler.prefix + ConfigHandler.pointsAddToFaction.replace("%faction%", factionName).replace("%points%", NumberFormatted.formatted(points)));
                    break;
                case "remove":
                    if (factionObject.getPoints() < points || points == 0) {
                        sender.sendMessage(ConfigHandler.prefix + ConfigHandler.cannotWithdrawMore);
                        return true;
                    }
                    factionObject.removePoints(points);
                    sender.sendMessage(ConfigHandler.prefix + ConfigHandler.pointsRemoveToFaction.replace("%faction%", factionName).replace("%points%", NumberFormatted.formatted(points)));
                    break;
                case "set":
                    factionObject.setPoints(points);
                    sender.sendMessage(ConfigHandler.prefix + ConfigHandler.pointsSetToFaction.replace("%faction%", factionName).replace("%points%", NumberFormatted.formatted(points)));
                    break;
                default:
            }
        }
        return false;
    }

    private boolean isNumber(String string) {
        try {
            Long.parseLong(string);
            return false;
        }
        catch (Exception e) {
            return true;
        }
    }
}