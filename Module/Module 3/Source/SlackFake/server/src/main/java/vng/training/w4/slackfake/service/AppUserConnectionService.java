package vng.training.w4.slackfake.service;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@Log4j2
public class AppUserConnectionService implements UserConnectionService {

    private final Multimap<String, ChatUserConnection> connections = MultimapBuilder.hashKeys().linkedListValues().build();

    @Override
    public void register(ChatUserConnection connection) {
        connections.put(connection.getUserId(), connection);
    }

    @Override
    public void unregister(ChatUserConnection connection) {
        connections.remove(connection.getUserId(), connection);
    }

    @Override
    public List<ChatUserConnection> getConnectionsOfUser(String userId) {
        return (List<ChatUserConnection>) connections.get(userId);
    }

    @Override
    public void sendMessage(String userId, ResponsePacket response) {
        List<ChatUserConnection> connections = getConnectionsOfUser(userId);
        sendAll(response, connections);
    }

    private static void sendAll(ResponsePacket response, Collection<ChatUserConnection> connections) {
        Iterator<ChatUserConnection> iterator = connections.iterator();
        while (iterator.hasNext()) {
            ChatUserConnection connection = iterator.next();
            if (connection.isClosed()) {
                iterator.remove();
                log.debug("Remove \"{}\" due to lost connection", connection.getAddress());
                continue;
            }
            try {
                connection.send(response);
                log.info("Send response to \"" + connection.getAddress() + "\":\n" + response);
            } catch (Exception e) {
                log.error("Error while sending message to user {}", connection.getUserId(), e);
            }
        }
    }

    @Override
    public void broadcast(ResponsePacket response) {
        sendAll(response, connections.values());
    }
}
