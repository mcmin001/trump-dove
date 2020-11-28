package com.trump.dove.server;


import com.trump.dove.common.config.SysConfigProvider;
import com.trump.dove.common.constant.SysConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DoveServer {
    private static final Logger logger = LoggerFactory.getLogger(DoveServer.class);

    public static void main(String[] args) {
        String ip = SysConfigProvider.getStringOrDefault(SysConstants.SERVER_SOCKET_PORT, "127.0.0.1");
        int port = SysConfigProvider.getIntegerOrDefault(SysConstants.SERVER_SOCKET_PORT, 8888);

        logger.info("=================================");
        logger.info("Trump-Dove IP:{} PORT:{}", ip, port);
        logger.info("=================================");

        try{
            DoveSocketServer doveSocketServer = new DoveSocketServer(ip, port);
            doveSocketServer.start();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }
}
