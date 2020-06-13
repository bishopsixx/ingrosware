package us.devs.ingrosware.hud;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import us.devs.ingrosware.hud.annotation.ComponentManifest;
import us.devs.ingrosware.traits.Configable;
import us.devs.ingrosware.traits.Hideable;
import us.devs.ingrosware.traits.Labelable;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class Component implements Labelable, Hideable, Configable {
    private float x, y, width, height, lastX, lastY;
    public String label;
    private boolean hidden, dragging;

    public Minecraft mc = Minecraft.getMinecraft();
    private ScaledResolution sr = new ScaledResolution(mc);

    public Component() {
        if (getClass().isAnnotationPresent(ComponentManifest.class)) {
            ComponentManifest componentManifest = getClass().getAnnotation(ComponentManifest.class);
            this.label = componentManifest.label();
            this.x = componentManifest.x();
            this.y = componentManifest.y();
            this.width = componentManifest.width();
            this.height = componentManifest.height();
            this.hidden = componentManifest.hidden();
        }
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    public void init() {

    }

    public void onDraw(ScaledResolution scaledResolution) {

    }

    public void onResize(ScaledResolution scaledResolution) {
        if (sr.getScaledWidth() < scaledResolution.getScaledWidth() && getX() > sr.getScaledWidth() - getWidth() - 20) {
            setX(scaledResolution.getScaledWidth() - getWidth() - 2);
        }
        if (sr.getScaledHeight() < scaledResolution.getScaledHeight() && getY() > sr.getScaledHeight() - getHeight() - 20) {
            setY(scaledResolution.getScaledHeight() - getHeight() - 2);
        }
        if (sr.getScaledHeight() != scaledResolution.getScaledHeight() || sr.getScaledWidth() != scaledResolution.getScaledWidth()) {
            sr = scaledResolution;
        }
    }

    public void onFullScreen(float w, float h) {
        if (sr.getScaledWidth() < w && getX() > sr.getScaledWidth() - getWidth() - 20) {
            setX(w - (sr.getScaledWidth() - getWidth()) - 2);
        }
        if (sr.getScaledHeight() < h && getY() > sr.getScaledHeight() - getHeight() - 20) {
            setY(h - (sr.getScaledHeight() - getHeight()) - 2);
        }
        if (sr.getScaledHeight() != new ScaledResolution(Minecraft.getMinecraft()).getScaledHeight() || sr.getScaledWidth() != new ScaledResolution(Minecraft.getMinecraft()).getScaledWidth()) {
            sr = new ScaledResolution(Minecraft.getMinecraft());
        }
    }

    @Override
    public void save(JsonObject directory) {

    }

    @Override
    public void load(JsonObject directory) {

    }

    public float getLastX() {
        return lastX;
    }

    public float getLastY() {
        return lastY;
    }

    public boolean isDragging() {
        return dragging;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public void setLastX(float lastX) {
        this.lastX = lastX;
    }

    public void setLastY(float lastY) {
        this.lastY = lastY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
