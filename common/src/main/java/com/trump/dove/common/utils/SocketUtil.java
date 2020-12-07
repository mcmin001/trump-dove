package com.trump.dove.common.utils;

import com.trump.dove.common.packet.DataPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketUtil {
    private static final Logger logger = LoggerFactory.getLogger(SocketUtil.class);

    //todo will complete here later ...
    public static DataPacket getDataPacket(SocketChannel socketChannel){
        if(null == socketChannel){
            logger.warn("get null socketChannel");
            return null;
        }
        //ByteBuffer buffer = socketChannel.
        return null;
    }
}
