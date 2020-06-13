package us.devs.ingrosware.module.impl.persistent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import tcb.bces.listener.Subscribe;
import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.event.impl.other.FullScreenEvent;
import us.devs.ingrosware.event.impl.render.Render2DEvent;
import us.devs.ingrosware.event.impl.other.ResizeEvent;
import us.devs.ingrosware.gui.hud.GuiHudEditor;
import us.devs.ingrosware.module.ModuleCategory;
import us.devs.ingrosware.module.annotation.Persistent;
import us.devs.ingrosware.module.types.PersistentModule;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Persistent(label = "Overlay", category = ModuleCategory.RENDER)
public class OverlayModule extends PersistentModule {

    @Subscribe
    public void onRender(Render2DEvent eventRender) {
        if (mc.gameSettings.showDebugInfo || mc.currentScreen instanceof GuiHudEditor) return;
        IngrosWare.INSTANCE.getComponentManager().getValues().forEach(hudComponent -> {
            if (hudComponent.getX() < 0) {
                hudComponent.setX(0);
            }
            if (hudComponent.getX() + hudComponent.getWidth() > new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth()) {
                hudComponent.setX(new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth() - hudComponent.getWidth());
            }
            if (hudComponent.getY() < 0) {
                hudComponent.setY(0);
            }
            if (hudComponent.getY() + hudComponent.getHeight() > new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight()) {
                hudComponent.setY(new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() - hudComponent.getHeight());
            }
            if (!hudComponent.isHidden()) hudComponent.onDraw(new ScaledResolution(mc));
        });
    }

    @Subscribe
    public void onScreenResize(ResizeEvent event) {
        IngrosWare.INSTANCE.getComponentManager().getValues().forEach(hudComponent -> {
            if (!hudComponent.isHidden()) {
                hudComponent.onResize(event.getSr());
            }
        });
    }

    @Subscribe
    public void onFullScreen(FullScreenEvent event) {
        IngrosWare.INSTANCE.getComponentManager().getValues().forEach(hudComponent -> {
            if (!hudComponent.isHidden()) {
                hudComponent.onFullScreen(event.getWidth(), event.getHeight());
            }
        });
    }

}
