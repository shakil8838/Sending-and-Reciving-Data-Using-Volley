package com.example.sendingandrecivingdatausingvolley.apis;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sendingandrecivingdatausingvolley.dataProviders.FetchDataProvider;
import com.example.sendingandrecivingdatausingvolley.dataProviders.SettingUpData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anonymous on 1/9/2018.
 */
public class BackgroundTask {

    Context context;
    ArrayList<FetchDataProvider> arrayList = new ArrayList<FetchDataProvider>();

    public BackgroundTask(Context context) {
        this.context = context;
    }

    public ArrayList<FetchDataProvider> getArrayList() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLs.FETCH_DATA_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int count = 0;
                        while (count < response.length()) {

                            try {
                                JSONObject jsonObject = response.getJSONObject(count);
                                FetchDataProvider fetchDataProvider = new FetchDataProvider(
                                        jsonObject.getString("Date"),
                                        jsonObject.getString("Time"),
                                        jsonObject.getString("Details"));
                                arrayList.add(fetchDataProvider);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        SettingUpData.getIntance(context).addToReequestQue(jsonArrayRequest);

        return arrayList;
    }
}
