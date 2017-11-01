package Donnees;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Model.User;

/**
 * Created by Tarek on 04/12/2016.
 */

public class DurationPerDate {
    public static String date;
    public static int duration;

    public static void test()
    {
        if(duration>60)
        {
            notifAdmin();
        }
    }


    public static void notifAdmin()
    {

    }

}
