package com.example.messagesc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private Context ctx;
    private RequestQueue requestQueue;

    private MySingleton(Context context)
    {
        ctx=context;
        requestQueue=getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context context)
    {
        return  new MySingleton(context);
    }
    public  void addToRequest(Request request)
    {
        requestQueue.add(request);
    }
}
