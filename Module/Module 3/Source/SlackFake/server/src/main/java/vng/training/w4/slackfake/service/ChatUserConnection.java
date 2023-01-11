package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.IOException;

public interface ChatUserConnection {

    void close() throws IOException;

    boolean isClosed();

    void send(ResponsePacket packet) throws IOException;

    String getUserId();

    String getAddress();

}
