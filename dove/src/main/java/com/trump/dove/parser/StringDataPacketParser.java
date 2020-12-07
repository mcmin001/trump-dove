package com.trump.dove.parser;

import com.trump.dove.common.constant.SysConstants;
import com.trump.dove.common.exception.DoveException;
import com.trump.dove.common.packet.DataPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class StringDataPacketParser implements DataPacketParser<String>{
    private static final Logger logger = LoggerFactory.getLogger(StringDataPacketParser.class);

    @Override
    public String parser(DataPacket dataPacket) {
        byte[] body = dataPacket.getBody();
        try {
            return new String(body, SysConstants.DEFAULT_ENCODE);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            throw new DoveException(e);
        }
    }
}
