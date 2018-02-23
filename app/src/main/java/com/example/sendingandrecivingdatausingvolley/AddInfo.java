package com.example.sendingandrecivingdatausingvolley;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sendingandrecivingdatausingvolley.apis.URLs;
import com.example.sendingandrecivingdatausingvolley.dataProviders.SettingUpData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddInfo extends AppCompatActivity {

    EditText addDate, addTime, addDetails;
    Button getAllDatas;
    AlertDialog.Builder alertBuilder;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        addDate = (EditText) findViewById(R.id.getDate);
        addTime = (EditText) findViewById(R.id.getTime);
        addDetails = (EditText) findViewById(R.id.getDetails);
        alertBuilder = new AlertDialog.Builder(getApplicationContext());

        requestQueue = Volley.newRequestQueue(getBaseContext());

        final Map<String, String> postData = new HashMap<>();
        postData.put("Date", String.valueOf(addDate.getText()));
        postData.put("Time", String.valueOf(addTime.getText()));
        postData.put("Details", String.valueOf(addDetails.getText()));

        getAllDatas = (Button) findViewById(R.id.getAllBtn);
        getAllDatas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isNetworkAvailable()) {
                    Toast.makeText(getApplicationContext(), "No Internet connection...", Toast.LENGTH_LONG).show();
                } else {

                    final String getDate, getTime, getDetails;
                    getDate = addDate.getText().toString();
                    getTime = addTime.getText().toString();
                    getDetails = addDetails.getText().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.SEND_DATA_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("Server Response", String.valueOf(response));
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                    Log.e("Something went wrong", error.toString());
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Date", getDate);
                            params.put("Time", getTime);
                            params.put("Details", getDetails);

                            return params;
                        }
                    };

                    requestQueue.add(stringRequest);
                }
            }
        });
    }

    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
