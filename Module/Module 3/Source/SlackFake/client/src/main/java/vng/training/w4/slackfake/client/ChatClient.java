package vng.training.w4.slackfake.client;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.Closeable;
import java.io.IOException;

public interface ChatClient extends Closeable {

    void start(String host, int port) throws Exception;

    ResponsePacket send(RequestPacket packet) throws Exception;
}
