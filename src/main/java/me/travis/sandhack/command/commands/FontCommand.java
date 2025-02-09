package me.travis.sandhack.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.util.ClientMessage;

public class FontCommand extends Command {

    public FontCommand() {
        super("Font");
    }

    @Override
    public void execute(String[] message) {
        if (message[0].equalsIgnoreCase("reset")) {
            Sandhack.GUI_FONT_MANAGER.reset();
            ClientMessage.sendMessage("Reset font");
            return;
        }
        if (message.length < 2) {
            ClientMessage.sendErrorMessage(ChatFormatting.BOLD + "Set" + ChatFormatting.RESET + ChatFormatting.RED + " to set font or" +
                    ChatFormatting.BOLD + " Size" + ChatFormatting.RESET + ChatFormatting.RED + " to set font size");
            return;
        }
        String mode = message[0];
        String name = message[1].replaceAll("_", " ");
        if (mode.equalsIgnoreCase("set")) {
            if (name.equalsIgnoreCase("random")) {
                ClientMessage.sendMessage("Set font to " + Sandhack.GUI_FONT_MANAGER.setRandomFont());
                return;
            }
            if (Sandhack.GUI_FONT_MANAGER.setFont(name)) {
                ClientMessage.sendMessage("Set font to " + ChatFormatting.BOLD + name);
            } else {
                ClientMessage.sendErrorMessage("Cannot find font " + ChatFormatting.BOLD + name);
            }
            return;
        }
        if (mode.equalsIgnoreCase("size")) {
            int size;
            try {
                size = Integer.parseInt(name);
            } catch (Exception ignored) {
                ClientMessage.sendErrorMessage("Size not valid");
                return;
            }
            Sandhack.GUI_FONT_MANAGER.setFontSize(size);
            ClientMessage.sendMessage("Font size set to " + size);
            return;
        }
        ClientMessage.sendErrorMessage(ChatFormatting.BOLD + "Set" + ChatFormatting.RESET + " to set font or" +
                ChatFormatting.BOLD + " Size" + ChatFormatting.RESET + " to set font size");
    }
}
