package vng.training.w4.slackfake.client.model;

import java.util.Date;

public interface Message {

    String getMessageId();

    String getSenderId();

    String getChannelId();

    String getContent();

    String getType();

    Date getDate();

    void setSenderId(String senderId);

    void setChannelId(String channelId);

    void setContent(String content);

    void setType(String type);

    void setDate(Date date);

}
