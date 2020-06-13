package us.devs.ingrosware.setting.impl;

import us.devs.ingrosware.setting.AbstractSetting;

import java.lang.reflect.Field;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public class StringSetting extends AbstractSetting<String> {

    public StringSetting(String label, Object object, Field field) {
        super(label, object, field);
    }
}
