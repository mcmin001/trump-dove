package com.trump.dove.server.queue;

import com.trump.dove.base.DoveBase;
import com.trump.dove.server.handler.ClientEventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ClientEventQueue implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(ClientEventQueue.class);

    private BlockingDeque<ClientEventHandler> clientEventHandlerQueue = new LinkedBlockingDeque<>(1024);

    public void addClient(ClientEventHandler eventHandler){
        clientEventHandlerQueue.add(eventHandler);
    }

    public void run(){
        while (DoveBase.alive){
            ClientEventHandler eventHandler = clientEventHandlerQueue.poll();
            if(null != eventHandler){
            }
        }
    }


    @Override
    public void close() throws IOException {
        logger.info("ClientEventQueue closed now.");
    }
}
