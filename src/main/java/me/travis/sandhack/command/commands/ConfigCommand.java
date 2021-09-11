package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.util.ClientMessage;

public class ConfigCommand extends Command {

    public ConfigCommand() {
        super("Config", "C");
    }

    @Override
    public void execute(String[] message) {
        String folder = message[0].replaceAll("_", " ") + "/";
        if (folder.equalsIgnoreCase("/")) {
            ClientMessage.sendErrorMessage("Invalid Folder");
            return;
        }
        Sandhack.CONFIG_MANAGER.setActiveConfigFolder(folder);
        ClientMessage.sendMessage("Set config folder to " + ChatFormatting.BOLD + folder.substring(0, folder.length() - 1));
    }

}
