package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.model.Channel;

import java.util.List;
import java.util.Set;

public interface ChannelService {

    List<String> getChannelIds(String userId);

    Channel getChannelById(String id);

    Channel createChannel(String title, Set<String> memberIds);

    void addMember(String channelId, String userId);

    void removeMember(String channelId, String userId);

    void deleteChannel(String channelId);

    void renameChannel(String channelId, String newTitle);

    void addMessage(String channelId, String messageId);

    void removeMessage(String channelId, String messageId);

    /**
     * @return id:
     *      0: success
     *      1: fail
     *      2: unauthorized
     * */
    int join(String channelId, String userId);

    List<String> readAllMessage(String channelId);
}
