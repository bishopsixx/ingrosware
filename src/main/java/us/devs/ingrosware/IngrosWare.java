package us.devs.ingrosware;

import net.minecraft.client.Minecraft;
import tcb.bces.bus.DRCEventBus;
import tcb.bces.bus.DRCExpander;
import us.devs.ingrosware.command.manager.CommandManager;
import us.devs.ingrosware.event.EventDispatcher;
import us.devs.ingrosware.font.FontManager;
import us.devs.ingrosware.hud.manager.ComponentManager;
import us.devs.ingrosware.module.manager.ModuleManager;
import us.devs.ingrosware.setting.SettingManager;
import us.devs.ingrosware.traits.Closeable;
import us.devs.ingrosware.traits.Labelable;
import us.devs.ingrosware.traits.Startable;

import java.io.File;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public enum IngrosWare implements Startable, Closeable, Labelable {
    INSTANCE;

    private DRCExpander<DRCEventBus> bus;
    private File baseDir;

    private ModuleManager moduleManager;
    private CommandManager commandManager;
    private ComponentManager componentManager;
    private FontManager fontManager;
    private final SettingManager settingManager = new SettingManager();

    @Override
    public void start() {
        this.baseDir = new File(Minecraft.getMinecraft().mcDataDir, "Ingros");
        final DRCEventBus baseBus = new DRCEventBus();
        baseBus.setDispatcher(EventDispatcher.class);
        this.bus = new DRCExpander<>(baseBus);

        this.fontManager = new FontManager(new File(baseDir, "fonts"));
        this.componentManager = new ComponentManager(new File(baseDir, "components"));
        this.moduleManager = new ModuleManager(new File(baseDir, "modules"));
        this.commandManager = new CommandManager(new File(baseDir, "commands"));
        this.fontManager.start();
        this.componentManager.start();
        this.moduleManager.start();
        this.commandManager.start();

        bus.bind();
    }

    @Override
    public void close() {
        if(!baseDir.exists())
            baseDir.mkdirs();
        this.fontManager.close();
        this.componentManager.close();
        this.moduleManager.close();
    }

    @Override
    public String getLabel() {
        return "IngrosWare";
    }

    public DRCExpander<DRCEventBus> getBus() {
        return bus;
    }

    public SettingManager getSettingManager() {
        return settingManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ComponentManager getComponentManager() {
        return componentManager;
    }
}
