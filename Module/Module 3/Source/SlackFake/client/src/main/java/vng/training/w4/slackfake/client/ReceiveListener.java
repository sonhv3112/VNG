package vng.training.w4.slackfake.client;

import vng.training.w4.slackfake.protobuf.ResponsePacket;

public interface ReceiveListener {

    void onReceive(ResponsePacket packet);

}
