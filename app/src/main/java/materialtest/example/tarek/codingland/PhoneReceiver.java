package materialtest.example.tarek.codingland;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import Donnees.DurationPerDate;
import Model.User;

public class PhoneReceiver extends BroadcastReceiver {

    public static String date;
    public static Context kk;
    public void test()
    {
        RequestQueue queue = Volley.newRequestQueue(kk);
        final String url = "http://xxx.com/codingland/addnotif.php?user="+User.id+"&temps="+DurationPerDate.duration+"&date="+DurationPerDate.date;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("users", User.id+"");
                params.put("temps", DurationPerDate.duration+"");
                params.put("dates",DurationPerDate.date+"");

                return params;
            }
        };
        queue.add(postRequest);
    }




    public PhoneReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {


    kk=context;

        StringBuffer sb = new StringBuffer();
        Uri contacts = CallLog.Calls.CONTENT_URI;
        Cursor managedCursor = context.getContentResolver().query(contacts, null, null, null, null);
        int number = managedCursor.getColumnIndex( CallLog.Calls.NUMBER );
        int duration1 = managedCursor.getColumnIndex(CallLog.Calls.DURATION);
        if( managedCursor.moveToLast() == true ) {
            String phNumber = managedCursor.getString( number );
            String callDuration = managedCursor.getString( duration1 );
            String dir = null;
            sb.append( "\nPhone Number:--- "+phNumber +" \nCall duration in sec :--- "+callDuration );
            sb.append("\n----------------------------------");
            Log.i("*****Call Summary******","Call Duration is:-------"+sb);
            //Toast.makeText(context, "Durée de l'appel = "+callDuration, Toast.LENGTH_SHORT).show();



            Calendar c = Calendar.getInstance();

            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            Toast.makeText(context, formattedDate, Toast.LENGTH_SHORT).show();
            if (DurationPerDate.date == null) {
                DurationPerDate.date = formattedDate;
            }

            if (formattedDate.equals(DurationPerDate.date)) {
                DurationPerDate.duration = DurationPerDate.duration+ Integer.parseInt(callDuration);
                //Toast.makeText(context, callDuration, Toast.LENGTH_SHORT).show();
                if(DurationPerDate.duration>=60)
                {
                   test();
                }

            } else {
                date = formattedDate;
                DurationPerDate.duration = Integer.parseInt(callDuration);
                Toast.makeText(context, DurationPerDate.duration, Toast.LENGTH_SHORT).show();

                if(DurationPerDate.duration>=60)
                {
                    test();
                }
            }
        }

        managedCursor.close();
    }

    void getmaxx(String s)
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        if (DurationPerDate.date == null) {
            DurationPerDate.date = formattedDate;
        }
        if (formattedDate.equals(DurationPerDate.date)) {
            DurationPerDate.duration += Integer.parseInt(s);
            //Toast.makeText(context, duration, Toast.LENGTH_SHORT).show();
            test();
        } else {
            date = formattedDate;
            DurationPerDate.duration = Integer.parseInt(s);
            //Toast.makeText(context, duration, Toast.LENGTH_SHORT).show();
            test();
        }
    }


}
