package us.devs.ingrosware.module.manager;

import us.devs.ingrosware.IngrosWare;
import us.devs.ingrosware.manager.impl.AbstractMapManager;
import us.devs.ingrosware.module.IModule;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.common.reflect.ClassPath;
import us.devs.ingrosware.module.types.ToggleableModule;
import us.devs.ingrosware.util.ClassUtil;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class ModuleManager extends AbstractMapManager<String, IModule> {
    private final File dir;

    public ModuleManager(File dir) {
        this.dir = dir;

        if(!dir.exists())
            dir.mkdirs();
    }

    @Override
    public void start() {
        loadInternalModules();
        loadExternalModules();

        getValues().forEach(IModule::init);
    }

    @Override
    public void close() {

    }

    private void register(IModule module) {
        put(module.getLabel(), module);
    }

    private void loadInternalModules() {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            final String modulePackage = "us.devs.ingrosware.module.impl";
            final ClassPath classpath = ClassPath.from(loader); // scans the class path used by classloader
            for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClassesRecursive(modulePackage)) {
                final Class<?> moduleClass = classInfo.load();
                final IModule module = (IModule) moduleClass.newInstance();
                register(module);

                System.out.println(String.format("[Ingros] Registered Module %s", classInfo.getSimpleName()));
            }

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void loadExternalModules() {
        try {
            final File file = new File(dir, "externals");
            if(!file.exists())
                file.mkdirs();
            if (ClassUtil.getClassesEx(file.getPath()).isEmpty()) System.out.println("[Ingros] No external modules found!");
            for (Class clazz : ClassUtil.getClassesEx(file.getPath())) {
                if (clazz != null && ToggleableModule.class.isAssignableFrom(clazz)) {
                    final ToggleableModule module = (ToggleableModule) clazz.newInstance();
                    register(module);
                    System.out.println(String.format("[Ingros] Found External Module %s", module.getLabel()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * could be done with a lambda/optional but they sloooow
     * @return
     */
    public List<ToggleableModule> getToggles() {
        final List<ToggleableModule> toggleableModules = new LinkedList<>();
        for(IModule module : getValues()) {
            if(module instanceof ToggleableModule)
                toggleableModules.add((ToggleableModule) module);
        }

        return toggleableModules;
    }


}
