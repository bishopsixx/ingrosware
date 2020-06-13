package us.devs.ingrosware.mixin.impl;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.event.impl.render.Render2DEvent;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Redirect(method = "updateCameraAndRender", at = @At(value = "INVOKE", target = "net/minecraft/client/gui/GuiIngame.renderGameOverlay(F)V"))
    private void updateCameraAndRender$renderGameOverlay(GuiIngame guiIngame, float partialTicks) {
        guiIngame.renderGameOverlay(partialTicks);
        IngrosWare.INSTANCE.getBus().post(new Render2DEvent(partialTicks, new ScaledResolution(Minecraft.getMinecraft())));
    }

}
