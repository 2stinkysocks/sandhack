package me.travis.sandhack.hack.hacks.chat;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.util.ClientMessage;

@Hack.Registration(name = "Totem Pop Counter", description = "counts totems that people have popped", category = Hack.Category.CHAT)
public class TotemPopCounter extends Hack {

    @Override
    public void onUpdate() {
        if (nullCheck()) return;
        if (!Sandhack.POP_MANAGER.toAnnouce.isEmpty()) {
            try {
                for (String string : Sandhack.POP_MANAGER.toAnnouce) {
                    ClientMessage.sendOverwriteClientMessage(string);
                }
                Sandhack.POP_MANAGER.toAnnouce.clear();
            } catch (Exception e) {
                //empty catchblock goo brrrrrrrrr
            }
        }
    }
}
