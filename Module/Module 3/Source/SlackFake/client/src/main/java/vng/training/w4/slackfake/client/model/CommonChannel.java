package vng.training.w4.slackfake.client.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class CommonChannel implements Channel, Serializable {

    private String channelId;
    private String title;
    private Set<String> memberIds;
    private List<String> messageIds;

    public CommonChannel(String channelId, String title, Set<String> memberIds, List<String> messageIds) {
        this.channelId = channelId;
        this.title = title;
        this.memberIds = memberIds;
        this.messageIds = messageIds;
    }

    @Override
    public String getChannelId() {
        return channelId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Set<String> getMemberIds() {
        return memberIds;
    }

    @Override
    public List<String> getMessageIds() {
        return messageIds;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setMemberIds(Set<String> memberIds) {
        this.memberIds = memberIds;
    }

    @Override
    public void setMessageIds(List<String> messageIds) {
        this.messageIds = messageIds;
    }

    @Override
    public void addMember(String userId) {
        this.memberIds.add(userId);
    }

}
