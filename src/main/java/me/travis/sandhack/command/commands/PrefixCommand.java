package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.command.Commands;
import me.travis.sandhack.util.ClientMessage;

public class PrefixCommand extends Command {

    public PrefixCommand() {
        super("Prefix");
    }

    @Override
    public void execute(String[] message) {
        if (message.length == 1) {
            String prefix = message[0];
            if (prefix.length() != 1) {
                ClientMessage.sendErrorMessage("Prefix not valid");
            } else {
                Commands.prefix = prefix;
                ClientMessage.sendMessage("Prefix set to " + ChatFormatting.BOLD + prefix);
            }
        }
    }
}
