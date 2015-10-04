package com.example.swaroop_sa2004.hackru;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class GetActivityView extends ActionBarActivity {
    ListView listView ;
    String id = "";
    String activity = "";
    String lat = "";
    String lng = "";
    String time = "";
    String numbOfPeep = "";
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_activity_view);

        getMethod();

      //  Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_activity_view, menu);
        return true;
    }
    public void getMethod() {
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String url = "http://45.79.148.237:3000/activity";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                try {
                    String rep1 = response.replaceAll("\\[", "").replaceAll("\\]", "");

                    JSONObject obj = new JSONObject(rep1);

                    id = obj.optString("_id");
                  //  Toast.makeText(getApplicationContext(), id, Toast.LENGTH_LONG).show();
                    activity = obj.optString("activityName");
                    lat =  obj.optString("lat");
                    lng = obj.optString("lng");
                    time = obj.optString("time");
                    Integer blah = (Integer) obj.opt("numberofPeople");
                    numbOfPeep = blah.toString();

                    listView = (ListView) findViewById(R.id.mainListView);
                    ArrayAdapter<String> listAdapter ;

                    String[] array = new String[] {activity, lat, lng};
                    ArrayList<String> stuff = new ArrayList<String>();
                    stuff.addAll( Arrays.asList(array) );
                    listAdapter = new ArrayAdapter<String>(context, R.layout.simplerow, stuff);
                    listView.setAdapter( listAdapter );
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "hell1", Toast.LENGTH_LONG).show();

                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        if (error.networkResponse == null) {
                            // if (error.getClass().equals(TimeoutError.class)) {
                            // Show timeout error message
                            Toast.makeText(getApplicationContext(), "Oops. Timeout error!", Toast.LENGTH_LONG).show();
                            //}
                        } else {
                            Toast.makeText(getApplicationContext(), "Goodbye1 world", Toast.LENGTH_LONG).show();
                        }

                    }
                });

// Add the request to the RequestQueue.
        mRequestQueue.add(stringRequest);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
