package me.travis.sandhack.hack.hacks.render;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.DoubleSetting;

@Hack.Registration(name = "Camera Clip", description = "f5 mode", category = Hack.Category.RENDER, isListening = false)
public class CameraClip extends Hack {

    public static CameraClip INSTANCE;

    public CameraClip() {
        INSTANCE = this;
    }

    public DoubleSetting distance = new DoubleSetting("Distance", 10.0, -10.0, 50.0, this);

}
