package com.trump.dove.common.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 系统配置变量
 */
public class SysConfigProvider {
    public static final Logger logger = LoggerFactory.getLogger(SysConfigProvider.class);

    private static final Properties properties = new Properties();

    /**
     * 配置文件初始化
     * @param sysConfigFilePath
     */
    public static void init(String sysConfigFilePath){
        if(StringUtils.isNotBlank(sysConfigFilePath)){
            try {
                properties.load(new FileInputStream(sysConfigFilePath));
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * get value
     * @param key
     * @return
     */
    public static String getString(String key){
        return properties.getProperty(key);
    }

    public static String getStringOrDefault(String key, String defaultValue){
        String obj = properties.getProperty(key);
        return null == obj ? defaultValue : String.valueOf(obj);
    }

    public static Integer getInteger(String key){
        String obj = properties.getProperty(key);
        return null == obj ? 0 : Integer.valueOf(obj);
    }

    public static Integer getIntegerOrDefault(String key, Integer defaultValue){
        String obj = properties.getProperty(key);
        return null == obj ? defaultValue : Integer.valueOf(obj);
    }

}
