package com.trump.dove.common.packet;

import java.io.Serializable;

public class DataPacket implements Serializable {
    private static final long serialVersionUID = 4423919530141585334L;

    private byte[] header;
    private byte[] body;

    public byte[] getHeader() {
        return header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
