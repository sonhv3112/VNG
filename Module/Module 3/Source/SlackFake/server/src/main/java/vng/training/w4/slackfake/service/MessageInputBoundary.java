package vng.training.w4.slackfake.service;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface MessageInputBoundary {
    ResponsePacket.SendMessageToChannel send(RequestPacket.SendMessageToChannel request);

    ResponsePacket.ReadMessage read(RequestPacket.ReadMessage request);
}
