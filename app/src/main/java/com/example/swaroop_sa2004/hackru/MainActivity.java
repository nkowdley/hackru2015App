package com.example.swaroop_sa2004.hackru;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {
    EditText editText;
    String what, when;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMap(View view) {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void ViewActivity(View view){
        Intent intent = new Intent(this, GetActivityView.class);
        startActivity(intent);
    }

    public void theRealPushMethod(View view) {
        editText = (EditText) findViewById(R.id.what);
        what = editText.getText().toString();
        editText = (EditText) findViewById(R.id.when);
        when = editText.getText().toString();
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", "nigga");
            obj.put("activity", "yomomma");
            obj.put("lat", "9");
            obj.put("lng", "8");
            obj.put("time", "all day");
            obj.put("numberofPeople", 3);

        } catch (JSONException e) {
        }
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String url = "http://45.79.148.237:3000/newAct/";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse == null) {
                        }
                    }
                });
        mRequestQueue.add(jsObjRequest);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void pushMethod(View view) {
        RequestQueue mRequestQueue;
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        String url = "http://172.31.69.25:3000/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Toast.makeText(getApplicationContext(), "Hello world", Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
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
