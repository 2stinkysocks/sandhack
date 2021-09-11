package me.travis.sandhack.hack.hacks.chat;

import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.HackPriority;

@Hack.Registration(name = "Clear Chatbox", description = "makes the chatbox clear", category = Hack.Category.CHAT, priority = HackPriority.Lowest)
public class ClearChatbox extends Hack {

    public static ClearChatbox INSTANCE;

    public ClearChatbox() {
        INSTANCE = this;
    }

}