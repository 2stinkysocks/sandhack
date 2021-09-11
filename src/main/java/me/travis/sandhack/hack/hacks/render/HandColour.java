package me.travis.sandhack.hack.hacks.render;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.ColourSetting;
import me.travis.sandhack.util.elements.Colour;

@Hack.Registration(name = "Hand Colour", description = "colours hands (only 9 months late jumpy)", category = Hack.Category.RENDER, isListening = false)
public class HandColour extends Hack {

    public static HandColour INSTANCE;

    public HandColour() {
        INSTANCE = this;
    }

    public ColourSetting colour = new ColourSetting("Colour", new Colour(255, 255, 255, 150), this);

}
