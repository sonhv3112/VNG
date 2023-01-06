package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface ChannelInputBoundary {
    ResponsePacket.JoinChannel join(RequestPacket.JoinChannel request);
}
