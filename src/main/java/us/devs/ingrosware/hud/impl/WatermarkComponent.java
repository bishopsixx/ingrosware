package us.devs.ingrosware.hud.impl;

import net.minecraft.client.gui.ScaledResolution;
import us.devs.ingrosware.hud.Component;
import us.devs.ingrosware.hud.annotation.ComponentManifest;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@ComponentManifest(label = "Watermark", x = 2, y = 2, width = 58, height = 18)
public class WatermarkComponent extends Component {

    @Override
    public void onDraw(ScaledResolution scaledResolution) {
        super.onDraw(scaledResolution);

        mc.fontRenderer.drawStringWithShadow("Test", getX(), getY(), -1);
    }
}
