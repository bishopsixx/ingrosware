package us.devs.ingrosware.mixin.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.devs.ingrosware.IngrosWare;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Mixin(GuiMainMenu.class)
public class MixinMainMenu {

    @Inject(method = "drawScreen", at = @At("RETURN"))
    private void init(CallbackInfo ci) {
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(IngrosWare.INSTANCE.getLabel(), 2, 2, -1);
    }

}
