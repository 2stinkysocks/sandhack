package me.travis.sandhack.hack.hacks.misc;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.event.events.BlockCollisionBoundingBoxEvent;
import me.travis.sandhack.event.processor.CommitEvent;
import me.travis.sandhack.event.processor.EventPriority;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.HackPriority;
import me.travis.sandhack.setting.type.BooleanSetting;
import me.travis.sandhack.setting.type.DoubleSetting;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWeb;
import org.lwjgl.input.Keyboard;

/**
 * @author Madmegsox1
 * @since 04/05/2021
 */

@Hack.Registration(name = "Anti Web", description = "stops faggots from trapping you in webs", category = Hack.Category.MISC, priority = HackPriority.High)
public class AntiWeb extends Hack {

    BooleanSetting disableBB = new BooleanSetting("Add BB", true, this);
    DoubleSetting bbOffset = new DoubleSetting("BB Offset", 0.0, -2.0, 2.0,this, s -> disableBB.getValue());
    BooleanSetting onGround = new BooleanSetting("On Ground", true, this);
    DoubleSetting motionY = new DoubleSetting("Set MotionY", 1.0, 0.0, 20.0, this);
    DoubleSetting motionX = new DoubleSetting("Set MotionX", 0.84, -1.0, 5.0, this);

    @CommitEvent(priority = EventPriority.LOW)
    public void bbEvent(BlockCollisionBoundingBoxEvent event){
        if(nullCheck())return;
        if(mc.world.getBlockState(event.getPos()).getBlock() instanceof BlockWeb){
            if(disableBB.getValue()) {
                event.setCancelled(true);
                event.setBoundingBox(Block.FULL_BLOCK_AABB.contract(0, bbOffset.getValue(), 0));
            }
        }
    }


    @Override
    public void onUpdate(){
        if(mc.player.isInWeb && !Sandhack.HACKS.ishackEnabled("Step")){
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindSneak.keyCode)){
                mc.player.isInWeb = true;
                mc.player.motionY *= motionY.getValue();
            }
            else if(onGround.getValue()){
                mc.player.onGround = false;
            }
            if(Keyboard.isKeyDown(mc.gameSettings.keyBindForward.keyCode) ||  Keyboard.isKeyDown(mc.gameSettings.keyBindBack.keyCode) || Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.keyCode)
                    || Keyboard.isKeyDown(mc.gameSettings.keyBindRight.keyCode)) {
                mc.player.isInWeb = false;
                mc.player.motionX *= motionX.getValue();
                mc.player.motionZ *= motionX.getValue();
            }

        }
    }
}
