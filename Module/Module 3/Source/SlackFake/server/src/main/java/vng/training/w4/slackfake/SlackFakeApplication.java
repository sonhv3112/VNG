package vng.training.w4.slackfake;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vng.training.w4.slackfake.server.netty.NettyServer;
import vng.training.w4.slackfake.server.nio.NIOServer;

import java.io.IOException;

@SpringBootApplication
public class SlackFakeApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(SlackFakeApplication.class, args);

//        NIOServer nioServer = context.getBean(NIOServer.class);
//        nioServer.start("localhost", 1108);

        NettyServer nettyServer = context.getBean(NettyServer.class);
        nettyServer.start("localhost", 1212);
    }

}
