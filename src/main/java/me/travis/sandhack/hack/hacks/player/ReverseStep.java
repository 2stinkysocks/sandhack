package me.travis.sandhack.hack.hacks.player;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.DoubleSetting;

@Hack.Registration(name = "Reverse Step", description = "pulls u down down down", category = Hack.Category.PLAYER)
public class ReverseStep extends Hack {

    DoubleSetting height = new DoubleSetting("Height", 2.0, 0.0, 10.0, this);

    @Override
    public void onUpdate() {
        if (nullCheck()) return;
        try {
            if (mc.player.isInLava() || mc.player.isInWater() || mc.player.isOnLadder() || Sandhack.HACKS.ishackEnabled("Speed")) return;
        } catch (Exception ignored) {
            return;
        }

        if (mc.player.onGround) { // idk if this makes the other checks invalid, probably does
            for (double y = 0.0; y < this.height.getValue() + 0.5; y += 0.01) {
                if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
