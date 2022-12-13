package com.example.examen_noviembre_2022;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Volley_Singleton {
    private static Volley_Singleton mySingleTon;
    private RequestQueue requestQueue;
    private static Context context;

    private Volley_Singleton(Context context){
        this.context=context;
        this.requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if (requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized Volley_Singleton getInstance(Context context){
        if (mySingleTon==null){
            mySingleTon=new Volley_Singleton(context);
        }
        return mySingleTon;
    }
    public<T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
