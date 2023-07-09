package one.telefon.sip2;

import org.pjsip.pjsua2.Buddy;
import org.pjsip.pjsua2.BuddyConfig;
import android.util.Log;

public class PjSipBuddy extends Buddy {
    private static String TAG = "PjSipBuddy";

    PjSipBuddy(PjSipAccount account, String destination) {
        super();
        BuddyConfig cfg = new BuddyConfig();
        cfg.setUri(destination);
        cfg.setSubscribe(false);

        try {
            create(account, cfg);
        } catch (Exception e) {
            Log.e(TAG, "Error while creating buddy", e);
        }
    }
}
