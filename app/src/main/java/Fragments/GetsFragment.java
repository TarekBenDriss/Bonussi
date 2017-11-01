package Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.User;
import materialtest.example.tarek.codingland.LoginActivity;
import materialtest.example.tarek.codingland.MainActivity;
import materialtest.example.tarek.codingland.R;


public class GetsFragment extends Fragment {
    ListView lv;
    HashMap m;
    List l ;//= new ArrayList();

    public GetsFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*

        m = new HashMap();
        m.put("titre","listView custom Adapater");
        l.add(m);

        m = new HashMap();
        m.put("contenu","Fragment");
        l.add(m);
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gets, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RequestQueue mRequestQueue = Volley.newRequestQueue(getContext());

        mRequestQueue.start();

        String url = "http://xxx.com/codingland/gettaches.php?id="+User.id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        l = new ArrayList();
                        System.out.println(response);
                        try
                        {

                            ArrayList users = new ArrayList();
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject j = array.getJSONObject(i);
                                HashMap<String,String> h = new HashMap<>();
                                h.put("tache",j.getString("tache"));
                                h.put("temps",j.getString("temps"));
                                l.add(h);
                            }

                        }
                        catch(Exception e)
                        {}
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Erreur" + error.getMessage());
                    }
                });
        mRequestQueue.add(stringRequest);


        lv=(ListView)getActivity().findViewById(R.id.lv);



        String[] from = {"tache","temps"};
        int[] to = new int[]{R.id.tache,R.id.temps};
        lv=(ListView)getActivity().findViewById(R.id.lv);
        SimpleAdapter s = new SimpleAdapter(getContext(),l,R.layout.todo_listview,from,to);
        lv.setAdapter(s);


    }

}
