package vng.training.w4.slackfake.service.security;

import vng.training.w4.slackfake.protobuf.RequestPacket;

public interface SecurityService {

    AuthenticateResult authenticate(RequestPacket request);

}
