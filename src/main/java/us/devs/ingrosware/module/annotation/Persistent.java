package us.devs.ingrosware.module.annotation;

import us.devs.ingrosware.module.ModuleCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Persistent {
    String label();
    ModuleCategory category();
}
