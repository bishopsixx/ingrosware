package us.devs.ingrosware.module.types;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.module.IModule;
import us.devs.ingrosware.module.ModuleCategory;
import us.devs.ingrosware.module.annotation.Persistent;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class PersistentModule implements IModule {
    private String label;
    private ModuleCategory category;

    public final Minecraft mc = Minecraft.getMinecraft();

    public PersistentModule() {
        if(getClass().isAnnotationPresent(Persistent.class)) {
            Persistent persistent = getClass().getAnnotation(Persistent.class);
            this.label = persistent.label();
            this.category = persistent.category();
        }
    }

    @Override
    public void init() {
        IngrosWare.INSTANCE.getBus().register(this);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public ModuleCategory getCategory() {
        return category;
    }

    @Override
    public void save(JsonObject destination) {

    }

    @Override
    public void load(JsonObject source) {

    }

    @Override
    public boolean isEnabled() {
        return mc.player != null;
    }
}
