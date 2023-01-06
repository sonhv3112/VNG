package vng.training.w4.slackfake.model;

import java.util.List;
import java.util.Set;

public interface Channel {

    String getChannelId();

    String getTitle();

    Set<String> getMemberIds();

    List<String> getMessageIds();

    void setTitle(String title);

    void setMemberIds(Set<String> memberIds);

    void setMessageIds(List<String> messageIds);

    void addMember(String userId);

}
