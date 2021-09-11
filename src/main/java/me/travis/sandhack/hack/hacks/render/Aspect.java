package me.travis.sandhack.hack.hacks.render;

import me.travis.sandhack.event.events.PerspectiveEvent;
import me.travis.sandhack.event.processor.CommitEvent;
import me.travis.sandhack.event.processor.EventPriority;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.setting.type.DoubleSetting;

/**
 * @author SankuGG
 * @since 01/05/2021
 *  -> src from prism
 */

@Hack.Registration(name = "Aspect", description = "Does aspect shit", category = Hack.Category.RENDER, isListening = false)
public class Aspect extends Hack{
    DoubleSetting aspect = new DoubleSetting("Aspect",  mc.displayWidth / mc.displayHeight + 0.0, 0.0 ,3.0, this);

    @CommitEvent(priority = EventPriority.LOW)
    public void onPerspectiveEvent(PerspectiveEvent event){
        event.setAspect(aspect.getValue().floatValue());
    }
}
