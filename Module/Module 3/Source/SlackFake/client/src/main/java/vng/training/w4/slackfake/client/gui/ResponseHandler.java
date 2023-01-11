package vng.training.w4.slackfake.client.gui;

import ch.qos.logback.core.net.server.Client;
import lombok.extern.log4j.Log4j2;
import vng.training.w4.slackfake.client.model.Channel;
import vng.training.w4.slackfake.client.model.CommonChannel;
import vng.training.w4.slackfake.client.model.CommonMessage;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public final class ResponseHandler {
    public static void handleResponse(ResponsePacket response) throws IOException {
        log.info("Receive response: {}", response);
        switch (response.getResponseCase()) {
            case LOGINRESPONSE:
                loginHandler(response);
                break;
            case LOGOUTRESPONSE:
                logoutHandler(response);
                break;
            case CREATECHANNELRESPONSE:
                createChannelHandler(response);
                break;
            case JOINCHANNELRESPONSE:
                joinChannelHandler(response);
                break;
            case LISTINGCHANNELRESPONSE:
                listingChannelHandler(response);
                break;
            case READMESSAGERESPONSE:
                readMessageHandler(response);
                break;
            case REGISTERRESPONSE:
                registerHandler(response);
                break;
            case SENDMESSAGETOCHANNELRESPONSE:
                sendMessageToChannelHandler(response);
                break;
            case READMESSAGEOFCHANNEL:
                readMessageOfChannelHandler(response);
                break;
            case NEWMESSAGERESPONSE:
                newMessageHandler(response);
                break;
            default:
                log.debug("Invalid request from server Netty!");
                break;
        }
    }

    private static void readMessageOfChannelHandler(ResponsePacket response) throws IOException {
        ResponsePacket.ReadMessageOfChannel readMessageOfChannelResponse = response.getReadMessageOfChannel();
        List<String> messageIds = new ArrayList<>();
        for (int i = 0; i < readMessageOfChannelResponse.getMessageIdsCount(); ++i) {
//            System.out.println(readMessageOfChannelResponse.getMessageIds(i));
            messageIds.add(readMessageOfChannelResponse.getMessageIds(i));
        }
        ChatClientGUI.addMessageIdsToChannels(messageIds);
    }

    private static void newMessageHandler(ResponsePacket response) throws IOException {
        ResponsePacket.NewMessage newMessageResponse = response.getNewMessageResponse();
        if (newMessageResponse.getStatus() == ResponsePacket.NewMessage.Status.SUCCESS)
            ChatClientGUI.addMessagesToChannels(new CommonMessage(newMessageResponse.getMessageId(),
                    newMessageResponse.getChannelId(),
                    newMessageResponse.getSenderId(),
                    newMessageResponse.getContent()));
    }

    private static void sendMessageToChannelHandler(ResponsePacket response) {
        ResponsePacket.SendMessageToChannel sendMessageToChannelResponse = response.getSendMessageToChannelResponse();
        if (sendMessageToChannelResponse.getStatus() == ResponsePacket.SendMessageToChannel.Status.SUCCESS) {

        }
    }

    private static void registerHandler(ResponsePacket response) {

    }

    private static void readMessageHandler(ResponsePacket response) throws IOException {
//        System.out.println("Hello");
        ResponsePacket.ReadMessage readMessageResponse = response.getReadMessageResponse();
        if (readMessageResponse.getStatus() == ResponsePacket.ReadMessage.Status.SUCCESS)
            ChatClientGUI.addMessagesToChannels(new CommonMessage(readMessageResponse.getMessageId(),
                    readMessageResponse.getChannelId(),
                    readMessageResponse.getSenderId(),
                    readMessageResponse.getContent()));
    }

    private static void listingChannelHandler(ResponsePacket response) {
        ResponsePacket.ListingChannel listingChannelResponse = response.getListingChannelResponse();
        List<Channel> channelList = new ArrayList<>();
        for (int i = 0; i < listingChannelResponse.getChannelIdsCount(); ++i) {
            channelList.add(new CommonChannel(
                    listingChannelResponse.getChannelIds(i),
                    listingChannelResponse.getChannelNames(i),
                    null, null));
        }
        ChatClientGUI.setChannels(channelList);
    }

    private static void joinChannelHandler(ResponsePacket response) {
        try {
            ChatClientGUI.requestListChannels();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createChannelHandler(ResponsePacket response) {
        try {
            ChatClientGUI.requestListChannels();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void logoutHandler(ResponsePacket response) {
        ChatClientGUI.close();
        LoginGUI.setVisible(true);
    }

    private static void loginHandler(ResponsePacket response) throws IOException {
        ResponsePacket.Login loginResponse = response.getLoginResponse();
        if (loginResponse.getStatus() == ResponsePacket.Login.Status.SUCCESS) {
            LoginGUI.loginSuccess(loginResponse.getAccessToken());
        } else {
            LoginGUI.loginFailed(loginResponse.getMessage());
        }
    }
}
