package com.example.messagesc;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class mesajC extends AppCompatActivity {
    String url = "http://sadakatsizcpre.tr.ht/ekle.php";
    String url2 = "http://sadakatsizcpre.tr.ht/getir.php";
    static TextView tv;
    static EditText ad;
    static Handler handler;
    Context context = this;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {


        final webservisislemleri wsi = new webservisislemleri(getApplicationContext(),url,url2);
        final notification nt = new notification();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesaj_sayfasi);
        tv = findViewById(R.id.txViewYazi);
        Button btn = findViewById(R.id.btnEkle);
        ad = findViewById(R.id.editDeger);
        if(serviscalisiyormu()){

        }else{
            startService(new Intent(context,servis.class));
        }


        handler = new Handler() {

            @Override
            public void handleMessage(Message msg){

                wsi.alinanVerileriNesneyeDoldur(String.valueOf(msg.obj));
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                wsi.yaziyiGonder();


               // Intent i = new Intent(getApplicationContext(),Notificatioss.class);
               // startActivity(i);
            }
        });

    }

    public boolean serviscalisiyormu(){
        ActivityManager servisYoneticisi= (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo servisinfo : servisYoneticisi.getRunningServices(Integer.MAX_VALUE)){
            if(context.getPackageName().equals(servisinfo.service.getPackageName())){
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(context,servis.class));
    }
}
