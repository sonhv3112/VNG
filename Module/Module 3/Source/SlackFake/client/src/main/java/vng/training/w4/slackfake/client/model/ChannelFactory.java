package vng.training.w4.slackfake.client.model;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface ChannelFactory {

    Channel createChannel(String title, Set<String> memberIds, @Nullable List<String> messageIds);

}
