package me.travis.sandhack.mixin.mixins;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.event.events.DeathEvent;
import me.travis.sandhack.util.Globals;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={NetHandlerPlayClient.class})
public class MixinNetHandlerPlayClient {
    @Inject(method={"handleEntityMetadata"}, at={@At(value="RETURN")}, cancellable=true)
    private void handleEntityMetadataHook(SPacketEntityMetadata packetIn, CallbackInfo info) {
        EntityPlayer player;
        Entity entity;
        if (Globals.mc.world != null && (entity = Globals.mc.world.getEntityByID(packetIn.getEntityId())) instanceof EntityPlayer
                && (player = (EntityPlayer)entity).getHealth() <= 0.0f) {
            Sandhack.EVENT_PROCESSOR.postEvent(new DeathEvent(player));
            Sandhack.POP_MANAGER.onDeath(player);
        }
    }
}

