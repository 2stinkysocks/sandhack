package me.travis.sandhack.hack.hacks.render;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.ColourSetting;
import me.travis.sandhack.util.elements.Colour;

/**
 * @author Madmegsox1
 * @since 28/04/2021
 */

@Hack.Registration(name = "PitbullEsp", description = "makes everyones skin pitbull", category = Hack.Category.RENDER, isListening = false)
public class Pitbull extends Hack {

    public static Pitbull INSTANCE;

    public Pitbull(){
        INSTANCE = this;
    }

    public ColourSetting texture = new ColourSetting("Texture",new Colour(255,255,255, 255), this);

}
