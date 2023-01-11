package vng.training.w4.slackfake.service.security;

import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vng.training.w4.slackfake.protobuf.RequestPacket;
import vng.training.w4.slackfake.protobuf.ResponsePacket;
import vng.training.w4.slackfake.utils.SecurityUtils;

import static vng.training.w4.slackfake.utils.StringUtils.decodeUsernameFromAccessToken;

@Service
public class AppSecurityService implements SecurityService {

    @Override
    public AuthenticateResult authenticate(RequestPacket request) {
        if (!SecurityUtils.isRequireAccessToken(request)) {
            return AuthenticateResult.SUCCESS;
        }
        String token = getAccessToken(request);
        boolean valid = false;
        String userId = null;
        try {
            userId = decodeUsernameFromAccessToken(token);
        } catch (SignatureException ignored) {}
        if (userId != null) {
            valid = true;
        }
        if (valid) {
            return new AuthenticateResult(true, userId, null);
        }
        return new AuthenticateResult(false, userId, null);
    }

    private static String getAccessToken(RequestPacket request) {
        final int number = request.getDataCase().getNumber();
        switch (number) {
            case RequestPacket.READMESSAGEOFCHANNEL_FIELD_NUMBER:
                return request.getReadMessageOfChannel().getAccessToken();
            case RequestPacket.CREATECHANNEL_FIELD_NUMBER:
                return request.getCreateChannel().getAccessToken();
            case RequestPacket.JOINCHANNEL_FIELD_NUMBER:
                return request.getJoinChannel().getAccessToken();
            case RequestPacket.LISTINGCHANNEL_FIELD_NUMBER:
                return request.getListingChannel().getAccessToken();
            case RequestPacket.SENDMESSAGETOCHANNEL_FIELD_NUMBER:
                return request.getSendMessageToChannel().getAccessToken();
            case RequestPacket.READMESSAGE_FIELD_NUMBER:
                return request.getReadMessage().getAccessToken();
            case RequestPacket.LOGOUT_FIELD_NUMBER:
                return request.getLogout().getAccessToken();
            default:
                throw new IllegalStateException("Unexpected value: " + number);
        }
    }

    private boolean isTokenValid(String token) {
        if (token == null || token.length() < 1) {
            return false;
        }
        return true;
    }

}
