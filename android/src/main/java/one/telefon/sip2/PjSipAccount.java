package one.telefon.sip2;

import one.telefon.sip2.dto.AccountConfigurationDTO;
import org.json.JSONObject;
import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.OnIncomingCallParam;
import org.pjsip.pjsua2.OnInstantMessageParam;
import org.pjsip.pjsua2.OnTypingIndicationParam;
import org.pjsip.pjsua2.OnInstantMessageStatusParam;
import org.pjsip.pjsua2.OnRegStateParam;
import org.pjsip.pjsua2.SendInstantMessageParam;
import org.pjsip.pjsua2.SendTypingIndicationParam;

public class PjSipAccount extends Account {

    private static String TAG = "PjSipAccount";

    /**
     * Last registration reason.
     */
    private String reason;

    private PjSipService service;

    private AccountConfigurationDTO configuration;

    private Integer transportId;

    public PjSipAccount(PjSipService service, int transportId, AccountConfigurationDTO configuration) {
        this.service = service;
        this.transportId = transportId;
        this.configuration = configuration;
    }

    public void register(boolean renew) throws Exception {
        setRegistration(renew);
    }

    public PjSipService getService() {
        return service;
    }

    public int getTransportId() {
        return transportId;
    }

    public AccountConfigurationDTO getConfiguration() {
        return configuration;
    }

    public String getRegistrationStatusText() {
        try {
            return getInfo().getRegStatusText();
        } catch (Exception e) {
            return "Connecting...";
        }
    }

    @Override
    public void onRegState(OnRegStateParam prm) {
        reason = prm.getReason();
        service.emmitRegistrationChanged(this, prm);
    }

    @Override
    public void onIncomingCall(OnIncomingCallParam prm) {
        PjSipCall call = new PjSipCall(this, prm.getCallId());
        service.emmitCallReceived(this, call);
    }

    @Override
    public void onInstantMessage(OnInstantMessageParam prm) {
        PjSipMessage message = new PjSipMessage(this, prm);
        service.emmitMessageReceived(this, message);
    }

    @Override
    public void onTypingIndication(OnTypingIndicationParam prm) {
        PjSipTypingIndication indication = new PjSipTypingIndication(this, prm);
        service.emmitTypingIndication(this, indication);
    }

    @Override
    public void onInstantMessageStatus(OnInstantMessageStatusParam prm) {
        PjSipInstantMessageStatus status = new PjSipInstantMessageStatus(this, prm);
        service.emmitInstantMessageStatus(this, status);
    }
    
    public void sendInstantMessage(String destination, String message) {
        try {
            SendInstantMessageParam prm = new SendInstantMessageParam();
            prm.setContentType("text/plain");
            prm.setContent(message);

            PjSipBuddy buddy = new PjSipBuddy(this, destination);
            buddy.sendInstantMessage(prm);
            buddy.delete();
            prm.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendTypingIndication(String destination, boolean isTyping) {
        try {
            SendTypingIndicationParam prm = new SendTypingIndicationParam();
            prm.setIsTyping(isTyping);

            PjSipBuddy buddy = new PjSipBuddy(this, destination);
            buddy.sendTypingIndication(prm);
            buddy.delete();
            prm.delete();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        try {
            JSONObject registration = new JSONObject();
            registration.put("status", getInfo().getRegStatus());
            registration.put("statusText", getInfo().getRegStatusText());
            registration.put("active", getInfo().getRegIsActive());
            registration.put("reason", reason);

            json.put("id", getId());
            json.put("uri", getInfo().getUri());
            json.put("name", configuration.getName());
            json.put("username", configuration.getUsername());
            json.put("domain", configuration.getDomain());
            json.put("password", configuration.getPassword());
            json.put("proxy", configuration.getProxy());
            json.put("transport", configuration.getTransport());

            json.put("contactParams", configuration.getContactParams());
            json.put("contactUriParams", configuration.getContactUriParams());

                    
            json.put("regServer", 
                    configuration.getRegServer());
            json.put("regTimeout", configuration.isRegTimeoutNotEmpty() ? String.valueOf(configuration.getRegTimeout()) : "");
            json.put("regContactParams", configuration.getRegContactParams());
            json.put("regHeaders", configuration.getRegHeaders());
            json.put("regOnAdd", configuration.isRegOnAdd());

            json.put("registration", registration);

            return json;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toJsonString() {
        return toJson().toString();
    }
}
