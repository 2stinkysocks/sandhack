package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.util.ClientMessage;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("Toggle", "T");
    }

    @Override
    public void execute(String[] message) {
        String name = message[0].replaceAll("_", " ");
        Hack hack = Sandhack.HACKS.getHackByName(name);
        if (hack != null) {
            hack.toggle();
        } else {
            ClientMessage.sendErrorMessage("Cannot find hack by name " + ChatFormatting.BOLD + name);
        }
    }
}
