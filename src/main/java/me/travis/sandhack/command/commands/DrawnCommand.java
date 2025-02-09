package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.util.ClientMessage;

public class DrawnCommand extends Command {

    public DrawnCommand() {
        super("Drawn");
    }

    @Override
    public void execute(String[] message) {
        Hack hack = Sandhack.HACKS.getHackByName(message[0].replaceAll("_", " "));
        if (hack == null) {
            ClientMessage.sendErrorMessage("Cannot find hack by name " + ChatFormatting.BOLD + message[0]);
            return;
        }
        if (Sandhack.HACKS.isDrawHack(hack)) {
            Sandhack.HACKS.removeDrawnHack(hack);
            ClientMessage.sendMessage("Removed " + ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + " from drawn list");
        } else {
            Sandhack.HACKS.addDrawHack(hack);
            ClientMessage.sendMessage("Added " + ChatFormatting.BOLD + hack.getName() + ChatFormatting.RESET + " to drawn list");
        }
    }
}
