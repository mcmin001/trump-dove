package com.trump.dove.server.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SocketChannel;

public class ClientEventHandler {
    private static Logger logger = LoggerFactory.getLogger(ClientEventHandler.class);

    private SocketChannel socketChannel;

    public ClientEventHandler(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }
}
