package vng.training.w4.slackfake.server.nio;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.server.ChatServer;
import vng.training.w4.slackfake.server.PacketResolver;
import vng.training.w4.slackfake.service.ChatUserConnection;
import vng.training.w4.slackfake.service.UserConnectionService;
import vng.training.w4.slackfake.tasks.PacketResolveState;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
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
    private final UserConnectionService userConnectionService;

    @Override
    public void start(String host, int port) throws Exception {
        Selector selector = Selector.open();
        ServerSocketChannel socket = ServerSocketChannel.open();

        InetSocketAddress addr = new InetSocketAddress(host, port);
        socket.bind(addr);
        socket.configureBlocking(false);

        socket.register(selector, SelectionKey.OP_ACCEPT);

        log.info("NIO Server. I'm a server and I'm waiting for new connection and buffer select...");

        while (socket.isOpen()) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iter = keys.iterator();
            while (iter.hasNext()) {
                SelectionKey myKey = iter.next();
                iter.remove();

                if (!myKey.isValid()) {
                    continue;
                }

                if (myKey.isAcceptable()) {
                    SocketChannel client = socket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    log.info("Connection Accepted: " + client.getLocalAddress() + "\n");
                    continue;
                }
                SocketChannel client = (SocketChannel) myKey.channel();
                if (myKey.isReadable()) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    try {
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
                    } catch (SocketException e) {
                        log.info("Connection closed by client: " + client.getLocalAddress());
                        myKey.cancel();
                        client.close();
                        continue;
                    }

                    RequestPacket request;
                    try {
                        request = RequestPacket.parseFrom(baos.toByteArray());
                    } catch (Exception e) {
                        log.debug("Error while parsing request", e);
                        continue;
                    }

                    String result = request.toString().trim();
                    log.info("Message received: " + result);

                    PacketResolveState state = new PacketResolveState(request);
                    packetResolver.resolve(state).get();

                    ResponsePacket.Builder responseBuilder = state.get(PacketResolveState.RESPONSE_BUILDER_KEY);
                    ResponsePacket response = responseBuilder.build();
                    log.info("Sending response: " + response);
                    sendResponse(client, response);

                    if (response.hasLogoutResponse()) {
                        if (response.getLogoutResponse().getStatus() == ResponsePacket.Logout.Status.SUCCESS) {
                            client.close();
                            log.info("Client closed");
                        }
                    } else if (response.hasLoginResponse()) {
                        if (response.getLoginResponse().getStatus() == ResponsePacket.Login.Status.SUCCESS) {
                            ChatUserConnection connection = new NIOConnection(this, client, state.get(PacketResolveState.USER_ID_KEY));
                            userConnectionService.register(connection);
                            log.info("Client registered");
                        }
                    }
                    continue;
                }

                if (myKey.isValid() && myKey.isWritable()) {
                    log.info("Writeable");
                    continue;
                }

                client.close();
                log.info("Client closed");
            }
        }
    }

    static void sendResponse(SocketChannel sc, ResponsePacket packet) throws IOException {
        byte[] data = packet.toByteArray();
        int length = data.length;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ByteBuffer lengthBuf = ByteBuffer.allocate(4).putInt(length);
        lengthBuf.flip();
        baos.write(lengthBuf.array());

        ByteBuffer dataBuf = ByteBuffer.wrap(data);
        dataBuf.flip();
        baos.write(dataBuf.array());

        ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());
        while (buffer.hasRemaining()) {
            sc.write(buffer);
        }

        /*byte[] bytes = packet.toByteArray();
        int length = bytes.length;
        ByteBuffer buffer = ByteBuffer.allocate(length + 4);
        buffer.put(bytes);
        buffer.putInt(length);
        while (buffer.hasRemaining()) {
            sc.write(buffer);
        }
        buffer.clear();*/
    }

}
