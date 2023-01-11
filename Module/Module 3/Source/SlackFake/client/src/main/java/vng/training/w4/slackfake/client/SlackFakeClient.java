package vng.training.w4.slackfake.client;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import vng.training.w4.slackfake.client.gui.LoginGUI;
import vng.training.w4.slackfake.client.netty.NettyChatClient;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

@SpringBootApplication
@Log4j2
public class SlackFakeClient {

    public static void main(String[] args) throws Exception {
//        ApplicationContext context = SpringApplication.run(Main.class, args);
        ChatClient client = new NettyChatClient(); // context.getBean(NettyChatClient.class);
        client.start("localhost", 1212);
        LoginGUI.start(client);

//        client.send(RequestPacket.newBuilder()
//                .setReadMessageOfChannel(RequestPacket.ReadMessageOfChannel
//                        .newBuilder()
//                        .setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInVzZXJuYW1lIjoiYWRtaW4yIn0.ykyv3FP7pySwf2JYAuOxRp6tbGgWqNd0txIhw7Tn8SI")
//                        .setChannelId("6dbcd503-c85f-4351-b698-80d6279addef")
//                        .setIndexStart(1)
//                        .setIndexEnd(10000)
//                        .build())
//                .build());

//        client.send(RequestPacket.newBuilder()
//                        .setListingChannel(RequestPacket.ListingChannel
//                                .newBuilder()
//                                .setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInVzZXJuYW1lIjoiYWRtaW4yIn0.ykyv3FP7pySwf2JYAuOxRp6tbGgWqNd0txIhw7Tn8SI")
//                                .build())
//                .build());

//        client.send(RequestPacket.newBuilder()
//                        .setJoinChannel(RequestPacket.JoinChannel
//                                .newBuilder()
//                                .setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInVzZXJuYW1lIjoiYWRtaW4yIn0.ykyv3FP7pySwf2JYAuOxRp6tbGgWqNd0txIhw7Tn8SI")
//                                .setChannelId("6dbcd503-c85f-4351-b698-80d6279addef")
//                                .build())
//                .build());


//        client.send(RequestPacket.newBuilder()
//                        .setLogin(RequestPacket.Login.newBuilder().setUsername("admin").setPassword("admin").build())
//                .build());
//        client.send(RequestPacket.newBuilder()
//                        .setRegister(RequestPacket.Register
//                                .newBuilder()
//                                .setUsername("admin2")
//                                .setPassword("admin2")
//                                .build())
//                .build());

//        client.send(RequestPacket.newBuilder()
//                        .setCreateChannel(RequestPacket.CreateChannel
//                                .newBuilder()
//                                .setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.gOmMA36bK1UsTYcfDWNDd-OrydK82fW9_bH1bISPJUU")
//                                .setChannelName("Channel 1")
//                                .build())
//                .build());
//        client.send(RequestPacket.newBuilder()
//                .setCreateChannel(RequestPacket.CreateChannel
//                        .newBuilder()
//                        .setAccessToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInVzZXJuYW1lIjoiYWRtaW4ifQ.gOmMA36bK1UsTYcfDWNDd-OrydK82fW9_bH1bISPJUU")
//                        .setChannelName("Channel 2")
//                        .build())
//                .build());
    }

}
