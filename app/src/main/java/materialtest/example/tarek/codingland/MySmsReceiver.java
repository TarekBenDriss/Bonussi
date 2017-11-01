package materialtest.example.tarek.codingland;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

public class MySmsReceiver extends BroadcastReceiver {



    public MySmsReceiver() {
    }
    final SmsManager sms = SmsManager.getDefault();

    final String KEY_INTENT_NUMBER="KEY_INTENT_NUMBER";
    final String KEY_INTENT_MSG="KEY_INTENT_MSG";

    @Override
    public void onReceive(Context context, Intent intent) {

        String numero = "";
        String message = "";

        Bundle extras = intent.getExtras();
        SmsMessage[] msgs = null;
        if(extras!=null)
        {
            Object[] pdus = (Object[]) extras.get("pdus"); //pdus houwa lobjet message , par exp jewek 5 pdus = 5 msg
            msgs = new SmsMessage[pdus.length];
            for (int i =0 ; i<msgs.length;i++)
            {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                numero = msgs[i].getOriginatingAddress();
                message = msgs[i].getMessageBody().toString();
            }
            //Toast.makeText(context, "Numero "+numero, Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, "Message "+message, Toast.LENGTH_SHORT).show();
            Intent intentCaller = new Intent(context,MainActivity.class);
            intentCaller.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intentCaller.putExtra(KEY_INTENT_NUMBER,numero);
            intentCaller.putExtra(KEY_INTENT_MSG,message);
            context.startActivity(intentCaller);
        }
    }
}
