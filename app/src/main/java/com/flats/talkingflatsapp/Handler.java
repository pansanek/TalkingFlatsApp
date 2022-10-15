package com.flats.talkingflatsapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Handler {

    public static Handler mInstanse;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private Handler(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

        }
        return requestQueue;
    }
    public static synchronized Handler getInstance(Context context){
        if(mInstanse ==null)
        {
            mInstanse = new Handler(context);
        }
        return mInstanse;
    }
    public <T>void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
