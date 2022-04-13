package com.github.indrodevteam.indroMain.commands.rank;

import com.github.indrodevteam.indroMain.ranks.RankStorage;
import com.github.indrodevteam.indroMain.teleports.PointStorage;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class CommandReloadRanks extends SubCommand {
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public List<String> getAliases() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Allows admins to update, and reload the plugins data saved on the files.";
    }

    @Override
    public String getSyntax() {
        return "/rank reload";
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            try {
                RankStorage.loadRanks();
                PointStorage.loadPoints();
                sender.sendMessage(ChatColor.AQUA + "Successfully reloaded the files!");
            } catch (IOException e) {
                sender.sendMessage(ChatColor.AQUA + "Error! Files could not be saved!");
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}