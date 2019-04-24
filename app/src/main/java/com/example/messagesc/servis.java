package com.example.messagesc;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class servis extends Service {
    Context context=this;
    final notification nt = new notification();

    int deger = 0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    Timer zamanlayi;
    static Handler yardimci;
    int sayac = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        zamanlayi = new Timer();
        yardimci = new Handler();
        Thread thread1;
        zamanlayi.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bilgiver();
            }
        }, 0, 5000);

    }

    void bilgiver() {
        mesajC.handler.post(new Runnable() {
            @Override
            public void run() {
                // long zaman = java.lang.System.currentTimeMillis();
                // SimpleDateFormat bilgi = new SimpleDateFormat("dd MMMM yyyy, EEEE / hh:mm");
                // final String sonuc = bilgi.format(new Date(zaman));

                StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://sadakatsizcpre.tr.ht/getir.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.length() != deger) {
                            Message mesajim = Message.obtain();
                            mesajim.obj = response;
                            mesajC.handler.sendMessage(mesajim);
                            deger = response.length();
                            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                            nt.Notify(context,manager);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                MySingleton.getInstance(context).addToRequest(stringRequest);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        zamanlayi.cancel();
    }
}