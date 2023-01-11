package vng.training.w4.slackfake.client.model;

import java.io.Serializable;
import java.util.Date;

public class CommonMessage implements Message, Serializable {

    private String messageId;

    private String channelId;

    private String userId;

    private String content;
    private String type;
    private Date date;

    public CommonMessage(String messageId, String channelId, String userId, String content) {
        this.messageId = messageId;
        this.channelId = channelId;
        this.userId = userId;
        this.content = content;
        this.type = "text";
        this.date = new Date();
    }

    @Override
    public String getMessageId() {
        return messageId;
    }

    @Override
    public String getChannelId() {
        return channelId;
    }

    @Override
    public String getSenderId() {
        return userId;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public void setSenderId(String userId) {
        this.userId = userId;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

}
