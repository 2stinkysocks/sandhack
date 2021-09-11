package me.travis.sandhack.util;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.hack.Hack;

public class HackUtil implements Globals {

    public static boolean shouldPause(Hack hack) {
        if (Sandhack.HACKS.ishackEnabled("Surround") && !hack.getName().equalsIgnoreCase("Surround")) {
            return true;
        }
        return false;
    }

}
