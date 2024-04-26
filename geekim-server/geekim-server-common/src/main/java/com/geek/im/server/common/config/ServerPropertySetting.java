package com.geek.im.server.common.config;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : ServerPropertySetting
 * @date : 2024/4/26 23:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class ServerPropertySetting {

    private final Map<String, Object> settings = new HashMap<>();

    public ServerPropertySetting() {

    }

    public ServerPropertySetting(Map<String, Object> settings) {
        this.settings.putAll(settings);
    }

    public void set(String key, Object value) {
        this.settings.put(key, value);
    }

    public void set(Pair<String, ?> pair) {
        this.settings.put(pair.getKey(), pair.getValue());
    }

    public void set(Map<String, Object> settings) {
        this.settings.putAll(settings);
    }

    public Map<String, Object> getSettings() {
        return this.settings;
    }


    public String getAsString(String key) {

        Object o = this.settings.get(key);
        if (Objects.isNull(o)) {
            return null;
        }
        return (String) this.settings.get(key);
    }

    public int getAsInt(String key) {
        Object o = this.settings.get(key);
        if (Objects.isNull(o)) {
            throw new RuntimeException("server property get setting:key=" + key + " is absent.");
        }
        return Integer.parseInt(this.settings.get(key).toString());
    }

    public boolean getAsBoolean(String key) {
        Object o = this.settings.get(key);
        if (Objects.isNull(o)) {
            throw new RuntimeException("server property get setting:key=" + key + " is absent.");
        }
        return Boolean.parseBoolean(o.toString());
    }


    public <T> T get(String key) {
        return (T) this.settings.get(key);
    }

    public <T> T get(String key, T defaultValue) {
        return (T) this.settings.getOrDefault(key, defaultValue);
    }


    public <T> T get(String key, Class<T> clazz) {
        return (T) this.settings.get(key);
    }

    public static ServerPropertySetting create() {
        return new ServerPropertySetting();
    }

    public static ServerPropertySetting defaultSetting() {

        return new ServerPropertySetting(ServerPropertyEntry.getDefaults().getSettings());
    }
}
