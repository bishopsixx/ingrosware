package us.devs.ingrosware.mixin.impl;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.event.impl.other.FullScreenEvent;
import us.devs.ingrosware.event.impl.other.KeyPressEvent;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Shadow
    public int displayWidth;
    @Shadow
    public int displayHeight;

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        IngrosWare.INSTANCE.start();
    }

    @Inject(method = "shutdownMinecraftApplet", at = @At("HEAD"))
    private void shutdownMinecraftApplet(CallbackInfo ci) {
        IngrosWare.INSTANCE.close();
    }

    @Inject(method = "runTickKeyboard", at = @At(value = "INVOKE", remap = false, target = "Lorg/lwjgl/input/Keyboard;getEventKey()I", ordinal = 0, shift = At.Shift.BEFORE))
    private void onKeyboard(CallbackInfo callbackInfo) {
        final int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
        if (Keyboard.getEventKeyState())
            IngrosWare.INSTANCE.getBus().post(new KeyPressEvent(i));
    }

    @Inject(method = "toggleFullscreen", at = @At("RETURN"))
    private void onToggleFullscreen(CallbackInfo info) {
        IngrosWare.INSTANCE.getBus().post(new FullScreenEvent(displayWidth, displayHeight));
    }

}
