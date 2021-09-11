package me.travis.sandhack.hack.hacks.player;

import me.travis.sandhack.event.events.PacketEvent;
import me.travis.sandhack.event.processor.CommitEvent;
import me.travis.sandhack.event.processor.EventPriority;
import me.travis.sandhack.hack.Hack;
import net.minecraft.network.play.client.CPacketCloseWindow;

@Hack.Registration(name = "XCarry", description = "carrys stuff", category = Hack.Category.PLAYER)
public class XCarry extends Hack {

    @CommitEvent(priority = EventPriority.LOW)
    public void onCloseGuiScreen(PacketEvent.Send event) {
        if (event.getPacket() instanceof CPacketCloseWindow) {
            CPacketCloseWindow packet = event.getPacket();
            if (packet.windowId == XCarry.mc.player.inventoryContainer.windowId) {
                event.setCancelled(true);
            }
        }
    }

}
