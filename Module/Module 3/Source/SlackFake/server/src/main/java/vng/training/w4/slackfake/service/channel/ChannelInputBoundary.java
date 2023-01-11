package vng.training.w4.slackfake.service.channel;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface ChannelInputBoundary {
    ResponsePacket.JoinChannel join(RequestPacket.JoinChannel request);
    ResponsePacket.ReadMessageOfChannel readMessage(RequestPacket.ReadMessageOfChannel request);
}
