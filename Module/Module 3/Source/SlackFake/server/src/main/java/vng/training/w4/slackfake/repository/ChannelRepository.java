package vng.training.w4.slackfake.repository;

import vng.training.w4.slackfake.model.Channel;

import java.util.List;

public interface ChannelRepository {

    void save(Channel channel);

    Channel findById(String channelId);

    List<Channel> getAll();

    void deleteById(String channelId);

}
