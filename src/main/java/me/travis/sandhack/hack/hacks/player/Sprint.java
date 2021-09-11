package me.travis.sandhack.hack.hacks.player;

import me.travis.sandhack.event.events.MoveEvent;
import me.travis.sandhack.event.processor.CommitEvent;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.EnumSetting;

import java.util.Arrays;

@Hack.Registration(name = "Sprint", description = "sprints automatically", category = Hack.Category.PLAYER)
public class Sprint extends Hack {

    public EnumSetting mode = new EnumSetting("Mode", "Legit", Arrays.asList("legit", "Rage"), this);

    @CommitEvent
    public void onMove(MoveEvent event) {
        if (event.getStage() == 1 && this.mode.is("Rage") && (mc.player.movementInput.moveForward != 0f ||
                mc.player.moveStrafing != 0f)) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onUpdate() {
        if (mode.is("Legit")) {
            if (mc.gameSettings.keyBindForward.isKeyDown()) {
                mc.player.setSprinting(true);
            }
        } else {
            mc.player.setSprinting(true);
        }
    }

    @Override
    public String getDisplayInfo() {
        return mode.getValue();
    }
}
