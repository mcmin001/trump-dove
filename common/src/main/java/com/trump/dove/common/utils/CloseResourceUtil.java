package com.trump.dove.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;

public class CloseResourceUtil {
    private static final Logger logger = LoggerFactory.getLogger(CloseResourceUtil.class);

    /**
     * 关闭资源
     * @param resources
     */
    public static void close(Closeable... resources){
        if(null == resources){
            return;
        }
        Arrays.stream(resources).forEach(res-> {
            try {
                res.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
}
