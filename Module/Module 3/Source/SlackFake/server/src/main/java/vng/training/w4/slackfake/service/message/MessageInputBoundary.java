package vng.training.w4.slackfake.service.message;

import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface MessageInputBoundary {
    ResponsePacket.NewMessage send(RequestPacket.SendMessageToChannel request);

    ResponsePacket.ReadMessage read(RequestPacket.ReadMessage request);
}
