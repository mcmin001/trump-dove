package com.trump.dove.server;

import com.trump.dove.base.DoveBase;
import com.trump.dove.common.utils.CloseResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class DoveSocketServer extends DoveBase implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(DoveSocketServer.class);

    private String ip;
    private int port;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private ClientReceiver clientReceiver;

    /**
     * construct
     * @param ip DoveSocketServer IP
     * @param port DoveSocketServer port
     */
    public DoveSocketServer(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    /**
     * statr DoveSocketServer
     */
    public void start(){
        try{
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(ip, port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            DoveBase.alive = true;
            logger.info("Dove Socket Server has been started success !");

            clientReceiver = new ClientReceiver(selector);
            clientReceiver.listen();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * close DoveSocketServer
     * @throws IOException
     */
    public void close() throws IOException {
        DoveBase.alive = false;

        if(null != selector){
            selector.close();
            CloseResourceUtil.close(selector);
        }

        if(null != serverSocketChannel){
            CloseResourceUtil.close(serverSocketChannel);
        }

        if(null != clientReceiver){
            CloseResourceUtil.close(clientReceiver);
        }

        logger.info("Dove Socket Server has been closed !");
    }

}
