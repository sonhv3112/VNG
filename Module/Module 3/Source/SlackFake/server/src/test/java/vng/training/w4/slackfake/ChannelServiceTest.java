package vng.training.w4.slackfake;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vng.training.w4.slackfake.model.Channel;
import vng.training.w4.slackfake.service.ChannelService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SlackFakeApplication.class)
@RunWith(SpringRunner.class)
public class ChannelServiceTest {

    @Autowired
    private ChannelService channelService;

    @Test
    public void testCreation() {
        channelService.createChannel("Channel 1", Set.of("admin"));
        String channelId = channelService.getChannelIds("admin").stream().findAny().get();

        assertNotNull(channelId);

        Channel channel = channelService.getChannelById(channelId);

        assertNotNull(channel);
    }

}
