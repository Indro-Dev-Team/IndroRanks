package indrocraft.indrocraftplugin.utils;

import indrocraft.indrocraftplugin.dataManager.ConfigTools;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class RankUtils {
    public static FileConfiguration config = ConfigTools.getFileConfig("rank.yml");

    public static void setRank(Player player, SQLUtils sqlUtils, String newRank) {
        sqlUtils.setData(newRank, "UUID", player.getUniqueId().toString(), "rank", "players");
    }

    public static void setNameColour(Player player, SQLUtils data, String newColour) {
        data.setData(newColour, "UUID", player.getUniqueId().toString(), "nameColour", "players");
    }

    public static void LoadRank(Player player, SQLUtils data) {
        String code = data.getString("rank", "UUID", player.getUniqueId().toString(), "players");
        String displayName = config.getString("ranks." + code + ".displayName");
        ChatColor colorA = getColour(1, player, data);
        ChatColor colorB = getColour(2, player, data);
        ChatColor name = getColour(3, player, data);
        player.setDisplayName(colorB + "[" + colorA + displayName + colorB + "] " + name + player.getName() + ChatColor.WHITE + "");
        player.setPlayerListName(colorB + "[" + colorA + displayName + colorB + "] " + name + player.getName() + ChatColor.WHITE + "");
    }

    public static ChatColor getColour(int colourNum, Player player, SQLUtils data) {
        String uuid = player.getUniqueId().toString();
        String code = data.getString("rank", "UUID", uuid, "players");
        String color = null;
        if (colourNum == 1) {
            color = config.getString("ranks." + code + ".primaryColour");
        } else if (colourNum == 2) {
            color = config.getString("ranks." + code + ".secondaryColour");
        } else if (colourNum == 3) {
            color = data.getString("nameColour", "UUID", uuid, "players");
        }

        return readColour(color);
    }

    public static ChatColor readColour(String color) {
        if (color.equalsIgnoreCase("gray")) {
            return ChatColor.GRAY;
        } else if (color.equalsIgnoreCase("dark_gray")) {
            return ChatColor.DARK_GRAY;
        } else if (color.equalsIgnoreCase("black")) {
            return ChatColor.BLACK;
        } else if (color.equalsIgnoreCase("dark_red")) {
            return ChatColor.DARK_RED;
        } else if (color.equalsIgnoreCase("red")) {
            return ChatColor.RED;
        } else if (color.equalsIgnoreCase("gold")) {
            return ChatColor.GOLD;
        } else if (color.equalsIgnoreCase("yellow")) {
            return ChatColor.YELLOW;
        } else if (color.equalsIgnoreCase("dark_green")) {
            return ChatColor.DARK_GREEN;
        } else if (color.equalsIgnoreCase("green")) {
            return ChatColor.GREEN;
        } else if (color.equalsIgnoreCase("aqua")) {
            return ChatColor.AQUA;
        } else if (color.equalsIgnoreCase("dark_aqua")) {
            return ChatColor.DARK_AQUA;
        } else if (color.equalsIgnoreCase("dark_blue")) {
            return ChatColor.DARK_BLUE;
        } else if (color.equalsIgnoreCase("blue")) {
            return ChatColor.BLUE;
        } else if (color.equalsIgnoreCase("light_purple")) {
            return ChatColor.LIGHT_PURPLE;
        } else if (color.equalsIgnoreCase("dark_purple")) {
            return ChatColor.DARK_PURPLE;
        }
        return ChatColor.WHITE;
    }
}