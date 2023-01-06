package vng.training.w4.slackfake.server.nio;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.server.ChatServer;
import vng.training.w4.slackfake.server.PacketResolver;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

@Log4j2
@AllArgsConstructor
@Component
public class NIOServer implements ChatServer {

    private final PacketResolver packetResolver;

    @Override
    public void start(String host, int port) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel socket = ServerSocketChannel.open();

        InetSocketAddress addr = new InetSocketAddress(host, port);
        socket.bind(addr);
        socket.configureBlocking(false);
        int ops = socket.validOps();

        SelectionKey selectKey = socket.register(selector, ops, null);
        while (true) {
            log.info("I'm a server and I'm waiting for new connection and buffer select...");
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();
            while (iter.hasNext()) {
                SelectionKey myKey = iter.next();
                iter.remove();
                if (myKey.isAcceptable()) {
                    SocketChannel client = socket.accept();
                    client.configureBlocking(false);
                    int clientOps = client.validOps();
                    clientOps |= SelectionKey.OP_WRITE;
                    clientOps |= SelectionKey.OP_READ;
                    SelectionKey clientSelectKey = client.register(selector, clientOps);
                    log.info("Connection Accepted: " + client.getLocalAddress() + "\n");
                    continue;
                }
                SocketChannel client = (SocketChannel) myKey.channel();
                if (myKey.isReadable() && myKey.isWritable()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    {
                        ByteBuffer buffer = ByteBuffer.allocate(256);
                        int bytesRead = client.read(buffer);
                        while (bytesRead > 0) {
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                baos.write(buffer.get());
                            }
                            buffer.clear();
                            bytesRead = client.read(buffer);
                        }
                    }

                    RequestPacket request;
                    try {
                        request = RequestPacket.parseFrom(baos.toByteArray());
                    } catch (Exception e) {
                        log.debug("Error while parsing request", e);
                        continue;
                    }

                    ResponsePacket response = packetResolver.resolve(request);;

                    String result = request.toString().trim();
                    log.info("Message received: " + result);
                    log.info("Sending response: " + response);
                    client.write(ByteBuffer.wrap(response.toByteArray()));

                    if (response.hasLogoutResponse() && response.getLogoutResponse().getStatus() == ResponsePacket.Logout.Status.SUCCESS) {
                        client.close();
                        log.info("Client closed");
                    }
                    continue;
                }

                client.close();
                log.info("Client closed due to unknown operation");
            }
        }
    }

}
