package us.devs.ingrosware.hud.manager;

import com.google.common.reflect.ClassPath;
import us.devs.ingrosware.hud.Component;
import us.devs.ingrosware.manager.impl.AbstractMapManager;
import us.devs.ingrosware.module.IModule;
import us.devs.ingrosware.util.ClassUtil;

import java.io.File;
import java.io.IOException;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class ComponentManager extends AbstractMapManager<String, Component> {
    private final File dir;

    public ComponentManager(File dir) {
        this.dir = dir;

        if(!dir.exists())
            dir.mkdirs();
    }

    @Override
    public void start() {
        loadInternalModules();
        loadExternalComponents();

        getValues().forEach(Component::init);
    }

    @Override
    public void close() {

    }

    private void register(Component component) {
        put(component.getLabel(), component);
    }

    private void loadInternalModules() {
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            final String modulePackage = "us.devs.ingrosware.hud.impl";
            final ClassPath classpath = ClassPath.from(loader);
            for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClassesRecursive(modulePackage)) {
                final Class<?> componentClass = classInfo.load();
                final Component component = (Component) componentClass.newInstance();
                register(component);

                System.out.println(String.format("[Ingros] Registered Component %s", classInfo.getSimpleName()));
            }

        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void loadExternalComponents() {
        try {
            final File file = new File(dir, "externals");
            if(!file.exists())
                file.mkdirs();
            if (ClassUtil.getClassesEx(file.getPath()).isEmpty()) System.out.println("[Ingros] No external components found!");
            for (Class clazz : ClassUtil.getClassesEx(file.getPath())) {
                if (clazz != null && Component.class.isAssignableFrom(clazz)) {
                    final Component module = (Component) clazz.newInstance();
                    register(module);
                    System.out.println(String.format("[Ingros] Found External Component %s", module.getLabel()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
