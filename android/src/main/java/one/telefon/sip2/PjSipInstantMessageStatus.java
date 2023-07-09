package one.telefon.sip2;

import org.json.JSONObject;
import org.pjsip.pjsua2.OnInstantMessageStatusParam;

public class PjSipInstantMessageStatus {

    private PjSipAccount account;

    private OnInstantMessageStatusParam prm;

    public PjSipInstantMessageStatus(PjSipAccount account, OnInstantMessageStatusParam prm) {
        this.account = account;
        this.prm = prm;
    }

    public OnInstantMessageStatusParam getParam() {
        return prm;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        try {
            // -----
            json.put("accountId", account.getId());

            // -----
            json.put("toUri", prm.getToUri());
            json.put("reason", prm.getReason());            
            json.put("code", prm.getCode());

            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toJsonString() {
        return toJson().toString();
    }

}
