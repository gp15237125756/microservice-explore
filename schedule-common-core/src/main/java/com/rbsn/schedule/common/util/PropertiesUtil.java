package com.rbsn.schedule.common.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.*;

/**
 * Properties文件载入工具类
 */
public final class PropertiesUtil extends PropertyPlaceholderConfigurer {
    private static Map<String, String> ctxPropertiesMap;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    /**
     * 取出String类型的Property.如果都为Null或内容错误则抛出异常.
     */
    public static String getString(String key) {
        try {
            return ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * 取出Integer类型的Property.如果都为Null或内容错误则抛出异常.
     */
    public static Integer getInteger(String key) {
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    /**
     * 取出Integer类型的Property.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        String value = getString(key);
        return StringUtils.isNotBlank(value) ? Integer.valueOf(value) : defaultValue;
    }

    /**
     * 取出Double类型的Property.如果都为Null或内容错误则抛出异常.
     */
    public static Double getDouble(String key) {
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    /**
     * 取出Double类型的Property.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public static Double getDouble(String key, Integer defaultValue) {
        String value = getString(key);
        return StringUtils.isNotBlank(value) ? Double.valueOf(value) : defaultValue;
    }

    /**
     * 取出Boolean类型的Property.如果都为Null抛出异常,如果内容不是true/false则返回false.
     */
    public static Boolean getBoolean(String key) {
        String value = getString(key);
        if (StringUtils.isBlank(value)) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    /**
     * 取出Boolean类型的Property.如果都为Null则返回Default值,如果内容不为true/false则返回false.
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        String value = getString(key);
        return StringUtils.isNotBlank(value) ? Boolean.valueOf(value) : defaultValue;
    }
}
