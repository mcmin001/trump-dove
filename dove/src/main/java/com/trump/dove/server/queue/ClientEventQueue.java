package com.trump.dove.server.queue;

import com.trump.dove.server.handler.ClientEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ClientEventQueue {
    private static final Logger logger = LoggerFactory.getLogger(ClientEventQueue.class);

    private BlockingDeque<ClientEventHandler> clientEventHandlerQueue = new LinkedBlockingDeque<>(1024);

    public void addClient(ClientEventHandler eventHandler){
        clientEventHandlerQueue.add(eventHandler);
    }


}
