package com.trump.dove.server;

import com.trump.dove.base.DoveBase;
import com.trump.dove.common.utils.CloseResourceUtil;
import com.trump.dove.server.handler.ClientEventHandler;
import com.trump.dove.server.queue.ClientEventQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientReceiver implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(ClientReceiver.class);

    private Selector serverSelector;

    private ClientEventQueue clientEventQueue;

    public ClientReceiver(Selector selector){
        this.serverSelector = selector;
    }

    public void listen(){
        clientEventQueue = new ClientEventQueue();
        logger.info("ClientReceiver listening now ......");
        try {
            do{
                int readyChannels = serverSelector.select();
                if(0 == readyChannels){
                    continue;
                }

                //一个SelectionKey键表示了一个特定的通道对象和一个特定的选择器对象之间的注册关系
                Set<SelectionKey> selectionKeySet = serverSelector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (DoveBase.alive && iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                }
            }while (DoveBase.alive);

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void processSelectKey(SelectionKey key){
        if(key.isValid() && key.isReadable()){
            clientEventQueue.addClient(new ClientEventHandler(key));
        }
    }

    @Override
    public void close() throws IOException {
        logger.info("ClientReceiver closed");
        CloseResourceUtil.close(clientEventQueue);
    }
}
