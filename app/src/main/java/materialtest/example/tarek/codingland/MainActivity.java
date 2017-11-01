package materialtest.example.tarek.codingland;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import Donnees.DurationPerDate;
import Fragments.GetsFragment;
import Model.User;

public class MainActivity extends AppCompatActivity {

    static Context v;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getFragmentManager().beginTransaction().replace(R.id.main_container,new IotFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new GetsFragment()).commit();
        v=getApplication();
    }

    public void not(int duration)
    {
        Toast.makeText(this, "kjshdfkjshdf", Toast.LENGTH_SHORT).show();
            RequestQueue queue = Volley.newRequestQueue(this);

            final String url = "http://xxx.com/codingland/addnotif.php?user="+User.id+"&temps="+duration+"&date="+PhoneReceiver.date;
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            //Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                            //Log.d("Error.Response", response);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("user", User.id+"");
                    params.put("temps", DurationPerDate.duration+"");
                    params.put("date",PhoneReceiver.date+"");

                    return params;
                }
            };
            queue.add(postRequest);





    }
}
