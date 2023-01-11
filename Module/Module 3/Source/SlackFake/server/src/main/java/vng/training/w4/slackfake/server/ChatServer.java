package vng.training.w4.slackfake.server;

import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.IOException;

public interface ChatServer {

    void start(String host, int port) throws Exception;
//    void sendToAllClientInChannel(String channelId, ResponsePacket response) throws Exception;
}
