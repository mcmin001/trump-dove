package com.trump.dove.parser;

import com.trump.dove.common.packet.DataPacket;

public interface DataPacketParser<T> {
    T parser(DataPacket dataPacket);
}
