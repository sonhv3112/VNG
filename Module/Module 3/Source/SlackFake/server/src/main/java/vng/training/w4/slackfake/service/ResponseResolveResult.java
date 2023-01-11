package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.protobuf.ResponsePacket;

public class ResponseResolveResult {

    private final String userId;
    private final ResponsePacket response;

    public ResponseResolveResult(String userId, ResponsePacket response) {
        this.userId = userId;
        this.response = response;
    }

    public boolean hasUserId() {
        return userId != null;
    }

    public String getUserId() {
        return userId;
    }

    public ResponsePacket getResponse() {
        return response;
    }

}
