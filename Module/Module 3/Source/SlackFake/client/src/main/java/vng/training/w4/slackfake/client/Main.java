package vng.training.w4.slackfake.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.IOException;

@SpringBootApplication
@Log4j2
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        NettyChatClient client = context.getBean(NettyChatClient.class);
        try (client) {
            client.start("localhost", 1212);
            ResponsePacket packet = client.send(RequestPacket.newBuilder()
                    .setRegister(RequestPacket.Register.newBuilder()
                            .setUsername("admin")
                            .setPassword("admin"))
                    .build());
            log.info("Response: {}", packet);
            ResponsePacket packet2 = client.send(RequestPacket.newBuilder()
                    .setLogin(RequestPacket.Login.newBuilder()
                            .setUsername("admin")
                            .setPassword("admin"))
                    .build());
            log.info("Response: {}", packet2);
        }
    }

}
