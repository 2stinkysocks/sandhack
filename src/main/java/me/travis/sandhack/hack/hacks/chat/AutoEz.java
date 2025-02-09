package me.travis.sandhack.hack.hacks.chat;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.event.events.DeathEvent;
import me.travis.sandhack.event.events.PacketEvent;
import me.travis.sandhack.event.processor.CommitEvent;
import me.travis.sandhack.hack.Hack;
import me.travis.sandhack.hack.HackPriority;
import me.travis.sandhack.setting.type.BooleanSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


@Hack.Registration(name = "Auto Ez", description = "lets people know ur clouted", category = Hack.Category.CHAT, priority = HackPriority.Lowest)
public class AutoEz extends Hack {

    public static AutoEz INSTANCE;

    public AutoEz() {
        INSTANCE = this;
    }

    private int delayCount;
    public final ConcurrentHashMap<String, Integer> targets = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        this.delayCount = 0;
    }

    @Override
    public void onUpdate() {
        delayCount++;
        for (Entity entity : mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.getHealth() <= 0) {
                    if (targets.containsKey(player.getName())) {
                        this.announceDeath();
                        this.targets.remove(player.getName());
                    }
                }
            }
        }
        targets.forEach((name, timeout) -> {
            if (timeout <= 0) {
                targets.remove(name);
            } else {
                targets.put(name, timeout - 1);
            }

        });
    }

    @SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event) {
        if (event.getTarget() instanceof EntityPlayer && !Sandhack.FRIEND_MANAGER.isFriend(event.getEntityPlayer().getName())) {
            this.targets.put(event.getTarget().getName(), 20);
        }
    }

    @CommitEvent
    public void onSendAttackPacket(PacketEvent.Send event) {
        CPacketUseEntity packet;
        if (event.getPacket() instanceof CPacketUseEntity && (packet = event.getPacket()).getAction() == CPacketUseEntity.Action.ATTACK && packet.getEntityFromWorld(mc.world) instanceof EntityPlayer && !Sandhack.FRIEND_MANAGER.isFriend(Objects.requireNonNull(packet.getEntityFromWorld(mc.world)).getName())) {
            this.targets.put(Objects.requireNonNull(packet.getEntityFromWorld(mc.world)).getName(), 20);
        }
    }

    @CommitEvent
    public void onEntityDeath(DeathEvent event) {
        if (this.targets.containsKey(event.player.getName())) {
            this.announceDeath();
            this.targets.remove(event.player.getName());
        }
    }

    private void announceDeath() {
        if (this.delayCount < 150) return;
        delayCount = 0;
        mc.player.connection.sendPacket(new CPacketChatMessage("sandhack on top ez!!!!"));
    }

}
