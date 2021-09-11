package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.hack.hacks.client.Gui;
import me.travis.sandhack.util.ClientMessage;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("Help", "H");
    }

    @Override
    public void execute(String[] message) {
        ClientMessage.sendMessage(ChatFormatting.BOLD + "Command list");
        ClientMessage.sendMessage("Your gui is currently bound to " + ChatFormatting.BOLD + Gui.INSTANCE.getBindName());
        for (Command command : Sandhack.COMMANDS.getCommands()) {
            ClientMessage.sendMessage(command.getNames().get(0));
        }
    }

}
