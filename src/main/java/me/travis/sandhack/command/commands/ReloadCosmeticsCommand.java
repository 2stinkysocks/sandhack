package me.travis.sandhack.command.commands;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.command.Command;
import me.travis.sandhack.util.ClientMessage;

/**
 * @author Madmegsox1
 * @since 01/05/2021
 */

public class ReloadCosmeticsCommand extends Command {
    public ReloadCosmeticsCommand(){
        super("ReloadCosmetics", "ReloadCosmetic");
    }

    @Override
    public void execute(String[] message) {
        Sandhack.COSMETIC_MANAGER.reload();
        ClientMessage.sendMessage("Reloaded Cosmetics!");
    }
}
