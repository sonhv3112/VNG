package vng.training.w4.slackfake.client;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;

public interface ChatClient extends Closeable {

    void start(String host, int port) throws Exception;

    void send(RequestPacket packet, Consumer<ResponsePacket> callback) throws IOException;

    default void send(RequestPacket packet) throws IOException {
        send(packet, null);
    }

    void addReceiveListener(ReceiveListener listener);

}
