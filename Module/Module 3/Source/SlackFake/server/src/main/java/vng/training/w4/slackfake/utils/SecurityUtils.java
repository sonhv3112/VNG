package vng.training.w4.slackfake.utils;

import com.google.protobuf.Descriptors;
import vng.training.w4.slackfake.protobuf.RequestPacket;

public final class SecurityUtils {

    private static final Descriptors.OneofDescriptor requestDataField;

    static {
        requestDataField = RequestPacket.getDescriptor().getOneofs().get(0);
    }

    /*public static boolean isRequireAccessToken(RequestPacket request) {
        Object obj = request.getField(request.getOneofFieldDescriptor(requestDataField));
        if (obj instanceof AbstractMessage) {
            AbstractMessage message = (AbstractMessage) obj;
            return message.hasField(message.getDescriptorForType().findFieldByName("accessToken"));
        }
        return false;
    }*/

    public static boolean isRequireAccessToken(RequestPacket request) {
        switch (request.getDataCase().getNumber()) {
            case RequestPacket.LOGIN_FIELD_NUMBER:
            case RequestPacket.REGISTER_FIELD_NUMBER:
                return false;
            default:
                return true;
        }
    }

}
