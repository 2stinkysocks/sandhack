package me.travis.sandhack.hack.hacks.player;

import me.travis.sandhack.hack.Hack;
@Hack.Registration(name = "MultiTask", description = "eat n shit", category = Hack.Category.PLAYER, isListening = false)
public class MultiTask extends Hack {

    public static MultiTask INSTANCE;

    public MultiTask() {
        INSTANCE = this;
    }

}
