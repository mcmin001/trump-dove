package com.trump.dove.server.handler;

import com.trump.dove.common.exception.DoveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class ClientEventHandler {
    private static Logger logger = LoggerFactory.getLogger(ClientEventHandler.class);

    private SelectionKey selectionKey;

    public ClientEventHandler(SelectionKey selectionKey){
        this.selectionKey = selectionKey;
    }

    public void process(){
        if(selectionKey.isValid()){

            if(selectionKey.isAcceptable()){

            }else if(selectionKey.isReadable()){

            }else if(selectionKey.isWritable()){

            }else{
                throw new DoveException("ClientEventHandler can not support this SelectionKey event");
            }
        }
    }
}
