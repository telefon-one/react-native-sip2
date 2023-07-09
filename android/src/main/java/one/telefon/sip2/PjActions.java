package one.telefon.sip2;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.util.Log;

import one.telefon.sip2.dto.CallSettingsDTO;
import one.telefon.sip2.dto.SipMessageDTO;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class PjActions {

    public static final String TAG = "PjActions";

    public static final String ACTION_START = "start";
    public static final String ACTION_CREATE_ACCOUNT = "account_create";
    public static final String ACTION_CHANGE_CODEC_SETTINGS = "change_codec_settings'";
    public static final String ACTION_REGISTER_ACCOUNT = "account_register";
    public static final String ACTION_DELETE_ACCOUNT = "account_delete";
    public static final String ACTION_MAKE_CALL = "call_make";
    public static final String ACTION_HANGUP_CALL = "call_hangup";
    public static final String ACTION_DECLINE_CALL = "call_decline";
    public static final String ACTION_ANSWER_CALL = "call_answer";
    public static final String ACTION_HOLD_CALL = "call_hold";
    public static final String ACTION_UNHOLD_CALL = "call_unhold";
    public static final String ACTION_MUTE_CALL = "call_mute";
    public static final String ACTION_UNMUTE_CALL = "call_unmute";
    public static final String ACTION_USE_SPEAKER_CALL = "call_use_speaker";
    public static final String ACTION_USE_EARPIECE_CALL = "call_use_earpiece";
    public static final String ACTION_XFER_CALL = "call_xfer";
    public static final String ACTION_XFER_REPLACES_CALL = "call_xfer_replace";
    public static final String ACTION_REDIRECT_CALL = "call_redirect";
    public static final String ACTION_DTMF_CALL = "call_dtmf";

    public static final String ACTION_SET_SERVICE_CONFIGURATION = "set_service_configuration";

    public static final String ACTION_ACTIVATEAUDIOSESSION_CALL = "call_activateaudiosession";
    public static final String ACTION_RINGING_CALL = "call_ringing";
    public static final String ACTION_PROGRESS_CALL = "call_progress";

    public static final String ACTION_SEND_MESSAGE = "one.telefon.message.send";    
    public static final String ACTION_SEND_TYPING_INDICATION = "one.telefon.typing.indication.send";


    public static final String EVENT_STARTED = "one.telefon.account.started";
    public static final String EVENT_ACCOUNT_CREATED = "one.telefon.account.created";
    public static final String EVENT_REGISTRATION_CHANGED = "one.telefon.registration.changed";
    public static final String EVENT_CALL_CHANGED = "one.telefon.call.changed";
    public static final String EVENT_CALL_TERMINATED = "one.telefon.call.terminated";
    public static final String EVENT_CALL_RECEIVED = "one.telefon.call.received";
    public static final String EVENT_CALL_SCREEN_LOCKED = "one.telefon.call.screen.locked";
    public static final String EVENT_MESSAGE_RECEIVED = "one.telefon.message.received";
    public static final String EVENT_TYPING_INDICATION_RECEIVED = "one.telefon.typing.indication.received";
    public static final String EVENT_MESSAGE_STATUS_RECEIVED = "one.telefon.instant.message.status.received";
    public static final String EVENT_HANDLED = "one.telefon.handled";

    public static Intent createStartIntent(int callbackId, ReadableMap configuration, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_START);
        intent.putExtra("callback_id", callbackId);

        formatIntent(intent, configuration);

        return intent;
    }

    public static Intent createSetServiceConfigurationIntent(int callbackId, ReadableMap configuration,
            Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_SET_SERVICE_CONFIGURATION);
        intent.putExtra("callback_id", callbackId);

        formatIntent(intent, configuration);

        return intent;
    }

    public static Intent createAccountCreateIntent(int callbackId, ReadableMap configuration, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_CREATE_ACCOUNT);
        intent.putExtra("callback_id", callbackId);

        formatIntent(intent, configuration);

        return intent;
    }

    public static Intent createAccountRegisterIntent(int callbackId, int accountId, boolean renew, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_REGISTER_ACCOUNT);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("account_id", accountId);
        intent.putExtra("renew", renew);

        return intent;
    }

    public static Intent createAccountDeleteIntent(int callbackId, int accountId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_DELETE_ACCOUNT);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("account_id", accountId);

        return intent;
    }

    public static Intent createMakeCallIntent(int callbackId, int accountId, String destination, ReadableMap settings,
            ReadableMap message, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_MAKE_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("account_id", accountId);
        intent.putExtra("destination", destination);

        if (settings != null) {
            intent.putExtra("settings", CallSettingsDTO.fromReadableMap(settings).toJson());
        }

        if (message != null) {
            intent.putExtra("message", SipMessageDTO.fromReadableMap(message).toJson());
        }

        return intent;
    }

    public static Intent createHangupCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_HANGUP_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    /*
     * public static Intent createActivateAudioSessionIntent(int callbackId, int
     * callId, Context context) {
     * Intent intent = new Intent(context, PjSipService.class);
     * intent.setAction(PjActions.ACTION_ACTIVATEAUDIOSESSION_CALL);
     * intent.putExtra("callback_id", callbackId);
     * intent.putExtra("call_id", callId);
     * 
     * return intent;
     * }
     */

    public static Intent createDeclineCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_DECLINE_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createAnswerCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_ANSWER_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createRingingCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_RINGING_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createProgressCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_PROGRESS_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createHoldCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_HOLD_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createUnholdCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_UNHOLD_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createMuteCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_MUTE_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createUnMuteCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_UNMUTE_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createUseSpeakerCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_USE_SPEAKER_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createUseEarpieceCallIntent(int callbackId, int callId, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_USE_EARPIECE_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);

        return intent;
    }

    public static Intent createXFerCallIntent(int callbackId, int callId, String destination, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_XFER_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);
        intent.putExtra("destination", destination);

        return intent;
    }

    public static Intent createXFerReplacesCallIntent(int callbackId, int callId, int destinationCallId,
            Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_XFER_REPLACES_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);
        intent.putExtra("dest_call_id", destinationCallId);

        return intent;
    }

    public static Intent createRedirectCallIntent(int callbackId, int callId, String destination, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_REDIRECT_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);
        intent.putExtra("destination", destination);

        return intent;
    }

    public static Intent createDtmfCallIntent(int callbackId, int callId, String digits, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_DTMF_CALL);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("call_id", callId);
        intent.putExtra("digits", digits);

        return intent;
    }

    public static Intent createChangeCodecSettingsIntent(int callbackId, ReadableMap codecSettings, Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_CHANGE_CODEC_SETTINGS);
        intent.putExtra("callback_id", callbackId);

        formatIntent(intent, codecSettings);

        return intent;
    }

    public static Intent createSendMessageIntent(int callbackId, int accountId, String destination, String message,
            Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_SEND_MESSAGE);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("account_id", accountId);
        intent.putExtra("destination", destination);
        intent.putExtra("message", message);

        return intent;
    }

    public static Intent createImTypingIntent(int callbackId, int accountId, String destination, boolean isTyping,
            Context context) {
        Intent intent = new Intent(context, PjSipService.class);
        intent.setAction(PjActions.ACTION_SEND_TYPING_INDICATION);
        intent.putExtra("callback_id", callbackId);
        intent.putExtra("account_id", accountId);
        intent.putExtra("destination", destination);
        intent.putExtra("isTyping", isTyping);

        return intent;
    }

    private static void formatIntent(Intent intent, ReadableMap configuration) {
        if (configuration == null) {
            return;
        }

        ReadableMapKeySetIterator it = configuration.keySetIterator();
        while (it.hasNextKey()) {
            String key = it.nextKey();

            switch (configuration.getType(key)) {
                case Null:
                    intent.putExtra(key, (String) null);
                    break;
                case String:
                    intent.putExtra(key, configuration.getString(key));
                    break;
                case Number:
                    intent.putExtra(key, configuration.getInt(key));
                    break;
                case Boolean:
                    intent.putExtra(key, configuration.getBoolean(key));
                    break;
                case Map:
                    intent.putExtra(key, (Serializable) formatMap(configuration.getMap(key)));
                    break;
                default:
                    Log.w(TAG, "Unable to put extra information for intent: unknown type \""
                            + configuration.getType(key) + "\"");
                    break;
            }
        }
    }

    private static Map<String, Object> formatMap(ReadableMap map) {
        Map<String, Object> value = new HashMap<>();
        ReadableMapKeySetIterator mapIt = map.keySetIterator();

        while (mapIt.hasNextKey()) {
            String mapKey = mapIt.nextKey();

            switch (map.getType(mapKey)) {
                case Null:
                    value.put(mapKey, null);
                    break;
                case String:
                    value.put(mapKey, map.getString(mapKey));
                    break;
                case Number:
                    value.put(mapKey, map.getInt(mapKey));
                    break;
                case Boolean:
                    value.put(mapKey, map.getBoolean(mapKey));
                    break;
                case Array:
                    value.put(mapKey, map.getArray(mapKey).toArrayList());
                    break;
                case Map:
                    value.put(mapKey, formatMap(map.getMap(mapKey)));
                    break;
                default:
                    Log.w(TAG,
                            "Unable to put extra information for intent: unknown type \"" + map.getType(mapKey) + "\"");
                    break;
            }
        }

        return value;
    }
}
