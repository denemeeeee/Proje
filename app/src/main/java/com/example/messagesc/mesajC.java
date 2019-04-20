package com.example.messagesc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesaj_sayfasi);

        tv = findViewById(R.id.txViewYazi);
        Button btn = findViewById(R.id.btnEkle);
        ad = findViewById(R.id.editDeger);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webservisislemleri wsi = new webservisislemleri(getApplicationContext(),url,url2);
                wsi.yaziyiGonder();
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder ibuilder = new Notification.Builder(getApplicationContext());
                ibuilder.setContentTitle("Mesaj Geldi")
                        .setContentText(ad.getText().toString())
                        .setSmallIcon(R.drawable.bildirim)

                        .setAutoCancel(true);


                Intent intent= new Intent(getApplicationContext(), mesajC.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
                ibuilder.setContentIntent(pendingIntent);
                //telefonun default olarak zil sesini alıp bildirim geldiğinde öttürtttümmm
                Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                ibuilder.setSound(uri);


                Notification nnesne = ibuilder.getNotification();
            manager.notify(1, nnesne);
            }
        });

    }

}
