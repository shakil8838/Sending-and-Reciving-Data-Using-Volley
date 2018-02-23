package com.example.sendingandrecivingdatausingvolley.dataProviders;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Anonymous on 1/8/2018.
 */
public class SettingUpData {

    private static SettingUpData mIntance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private SettingUpData(Context context){

        mContext = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized SettingUpData getIntance(Context context){

        if (mIntance==null){
            mIntance = new SettingUpData(context);
        }
        return mIntance;
    }

    public RequestQueue getRequestQueue(){

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T>void addToReequestQue(Request<T> request){

        requestQueue.add(request);
    }
}
