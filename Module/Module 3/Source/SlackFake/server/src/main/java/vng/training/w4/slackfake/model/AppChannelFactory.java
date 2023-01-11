package vng.training.w4.slackfake.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class AppChannelFactory implements ChannelFactory {

    @Override
    public Channel createChannel(String title, Set<String> memberIds, List<String> messageIds) {
        return new CommonChannel(RandomStringUtils.randomAlphabetic(3), title, memberIds, messageIds);
    }

}
