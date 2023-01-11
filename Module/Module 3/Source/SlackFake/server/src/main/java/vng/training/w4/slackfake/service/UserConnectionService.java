package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.util.List;

public interface UserConnectionService {

    void register(ChatUserConnection connection);

    void unregister(ChatUserConnection connection);

    List<ChatUserConnection> getConnectionsOfUser(String userId);

    void sendMessage(String userId, ResponsePacket response);

    void broadcast(ResponsePacket response);
}
