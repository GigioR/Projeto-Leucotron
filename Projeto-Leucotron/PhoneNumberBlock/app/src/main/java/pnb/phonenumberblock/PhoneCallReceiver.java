package pnb.phonenumberblock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;
public class PhoneCallReceiver extends BroadcastReceiver {

    private TelephonyManager telephony;

    @Override
    public void onReceive(Context context, Intent intent) {
        telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        PhoneCallStateListener customPhoneListener = new PhoneCallStateListener(context);
        telephony.listen(customPhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

    }

    public void onDestroy() {
        telephony.listen(null, PhoneStateListener.LISTEN_NONE);
    }
}

/*
public class PhoneCallReceiver extends BroadcastReceiver {
 Context context = null;
 private static final String TAG = "Phone call";
 private ITelephony telephonyService;

 @Override
 public void onReceive(Context context, Intent intent) {
  Log.v(TAG, "Receving....");
  TelephonyManager telephony = (TelephonyManager)
  context.getSystemService(Context.TELEPHONY_SERVICE);
  try {
   Class c = Class.forName(telephony.getClass().getName());
   Method m = c.getDeclaredMethod("getITelephony");
   m.setAccessible(true);
   telephonyService = (ITelephony) m.invoke(telephony);
   //telephonyService.silenceRinger();
   telephonyService.endCall();
  } catch (Exception e) {
   e.printStackTrace();
  }

 }
*/