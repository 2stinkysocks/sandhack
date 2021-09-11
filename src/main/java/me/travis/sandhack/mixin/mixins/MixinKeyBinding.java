package me.travis.sandhack.mixin.mixins;

import me.travis.sandhack.Sandhack;
import me.travis.sandhack.event.events.KeyEvent;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={KeyBinding.class})
public class MixinKeyBinding {
    @Shadow
    private boolean pressed;

    @Inject(method={"isKeyDown"}, at={@At(value="RETURN")}, cancellable=true)
    private void isKeyDown(CallbackInfoReturnable<Boolean> info) {
        KeyEvent event = new KeyEvent(0, info.getReturnValue(), this.pressed);
        Sandhack.EVENT_PROCESSOR.postEvent(event);
        info.setReturnValue(event.info);
    }
}

