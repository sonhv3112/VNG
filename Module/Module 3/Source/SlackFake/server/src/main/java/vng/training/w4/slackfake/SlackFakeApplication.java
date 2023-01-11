package vng.training.w4.slackfake;

import com.google.common.collect.Sets;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vng.training.w4.slackfake.model.User;
import vng.training.w4.slackfake.server.netty.NettyServer;
import vng.training.w4.slackfake.service.channel.ChannelService;
import vng.training.w4.slackfake.service.user.UserService;
//import vng.training.w4.slackfake.server.nio.NIOServer;

import java.io.IOException;

@SpringBootApplication
public class SlackFakeApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(SlackFakeApplication.class, args);

//        NIOServer nioServer = context.getBean(NIOServer.class);
//        nioServer.start("localhost", 1212);
        UserService service = context.getBean(UserService.class);
        User a = service.getUserByUsername("test1");
        User b = service.getUserByUsername("test2");

        if (a == null && b == null) {
            System.out.println("Creating test users");
            service.register("test1", "123", "Test A");
            a = service.getUserByUsername("test1");
            service.register("test2", "123", "Test B");
            b = service.getUserByUsername("test2");
            System.out.println("Created test users");

            ChannelService channelService = context.getBean(ChannelService.class);
            channelService.createChannel("tc", Sets.newHashSet(a.getUserId(), b.getUserId()));
            channelService.createChannel("tc2", Sets.newHashSet(a.getUserId(), b.getUserId()));
            System.out.println("Created channels");
        } else {
            System.out.println("Test users already exist");
        }

        NettyServer nettyServer = context.getBean(NettyServer.class);
        nettyServer.start("localhost", 1212);
    }

}
