package one.telefon.sip2;

import org.json.JSONObject;
import org.pjsip.pjsua2.OnTypingIndicationParam;

public class PjSipTypingIndication {

    private PjSipAccount account;

    private OnTypingIndicationParam prm;

    public PjSipTypingIndication(PjSipAccount account, OnTypingIndicationParam prm) {
        this.account = account;
        this.prm = prm;
    }

    public OnTypingIndicationParam getParam() {
        return prm;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        try {
            // -----
            json.put("accountId", account.getId());

            // -----
            json.put("contactUri", prm.getContactUri());
            json.put("fromUri", prm.getFromUri());
            json.put("toUri", prm.getToUri());
            json.put("isTyping", prm.getIsTyping());

            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toJsonString() {
        return toJson().toString();
    }

}
