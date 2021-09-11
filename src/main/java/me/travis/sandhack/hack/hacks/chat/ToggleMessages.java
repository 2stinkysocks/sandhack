package me.travis.sandhack.hack.hacks.chat;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.HackPriority;
import me.travis.sandhack.setting.type.BooleanSetting;

@Hack.Registration(name = "ToggleMsgs", description = "Says in chat when you toggle something", category = Hack.Category.CHAT, priority = HackPriority.Lowest)
public class ToggleMessages extends Hack {

    public static ToggleMessages INSTANCE;

    public ToggleMessages() {
        INSTANCE = this;
    }

    public BooleanSetting compact = new BooleanSetting("Compact", true, this);
}
