package pnb.phonenumberblock;
import java.lang.reflect.Method;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class PhoneCallStateListener extends PhoneStateListener {

    private Context context;
    public PhoneCallStateListener(Context context){
        this.context = context;
    }


    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        SharedPreferences prefs=PreferenceManager.getDefaultSharedPreferences(context);

        switch (state) {

            case TelephonyManager.CALL_STATE_RINGING:

                String block_number = prefs.getString("block_number", null);// "5535999585149";
                AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
                //Turn ON the mute
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                } else {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                }
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                try {
                    Toast.makeText(context, "in"+block_number, Toast.LENGTH_LONG).show();
                    Class clazz = Class.forName(telephonyManager.getClass().getName());
                    Method method = clazz.getDeclaredMethod("getITelephony");
                    method.setAccessible(true);
                    ITelephony telephonyService;
                    telephonyService = (ITelephony) method.invoke(telephonyManager);
                    //Checking incoming call number
                    System.out.println("Call "+block_number);

                    if (incomingNumber.equalsIgnoreCase("+91"+block_number)) {
                        //telephonyService.silenceRinger();//Security exception problem
                        telephonyService = (ITelephony) method.invoke(telephonyManager);
                        telephonyService.silenceRinger();
                        System.out.println(" in  "+block_number);
                        telephonyService.endCall();
                    }
                } catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }
                //Turn OFF the mute
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                } else {
                    audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
                }
                break;
            case PhoneStateListener.LISTEN_CALL_STATE:
                break;
            default:
                break;

        }
        super.onCallStateChanged(state, incomingNumber);
    }
}