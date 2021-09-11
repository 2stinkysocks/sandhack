package me.travis.sandhack.hack.hacks.misc;

import me.travis.sandhack.RPC;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.HackPriority;


@Hack.Registration(name = "DiscordRPC", description = "It is discordrpc !", category = Hack.Category.MISC, priority = HackPriority.Lowest)
public class DiscordRPC extends Hack {


    public void onEnable() {
        RPC.startRPC();
    }

    public void onDisable() {
        RPC.stopRPC();
    }
}
