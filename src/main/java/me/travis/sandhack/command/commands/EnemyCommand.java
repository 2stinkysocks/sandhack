package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.util.ClientMessage;
import me.travis.sandhack.util.elements.WurstplusPlayer;

public class EnemyCommand extends Command {

    public EnemyCommand() {
        super("Enemy", "E");
    }

    @Override
    public void execute(String[] message) {
        if (message.length == 0) {
            ClientMessage.sendErrorMessage("Need more info than that mate");
            return;
        }
        if (message.length == 1) {
            if (message[0].equalsIgnoreCase("list")) {
                if (Sandhack.ENEMY_MANAGER.hasEnemies()) {
                    ClientMessage.sendMessage(ChatFormatting.BOLD + "Listing enemies");
                    for (WurstplusPlayer player : Sandhack.ENEMY_MANAGER.getEnemies()) {
                        ClientMessage.sendMessage(player.getName());
                    }
                } else {
                    ClientMessage.sendMessage("u got no enemies :)");
                }
            } else if (message[0].equalsIgnoreCase("clear")) {
                Sandhack.ENEMY_MANAGER.clear();
                ClientMessage.sendMessage("Cleared enemies list");
            } else {
                ClientMessage.sendErrorMessage("Enemy <add/del/list/clear>");
            }
            return;
        }
        String word1 = message[0];
        String word2 = message[1];
        if (word1 == null) return;
        switch (word1) {
            case "add":
                if (word2 == null) {
                    ClientMessage.sendErrorMessage("need name");
                    return;
                }
                Sandhack.ENEMY_MANAGER.addEnemy(word2);
                ClientMessage.sendMessage(ChatFormatting.GREEN + word2 + ChatFormatting.RESET + " is now your enemy");
                break;
            case "del":
                Sandhack.ENEMY_MANAGER.removeEnemy(word2);
                ClientMessage.sendMessage(ChatFormatting.RED + word2 + ChatFormatting.RESET + " is no longer your enemy");
                if (word2 == null) {
                    ClientMessage.sendErrorMessage("need name");
                    return;
                }
                break;
            default:
                ClientMessage.sendErrorMessage("enemy <add/del/list>");
        }
    }
}
