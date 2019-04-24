package com.example.messagesc;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static com.example.messagesc.mesajC.ad;

public class notification {

    public void Notify(Context context,NotificationManager manager)
    {

        Notification.Builder ibuilder = new Notification.Builder(context);
        ibuilder.setContentTitle("Lovely Day")
                .setContentText("")
                .setSmallIcon(R.drawable.bildirim)
                .setAutoCancel(true);


        Intent intent= new Intent(context,mesajC.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, 0);
        ibuilder.setContentIntent(pendingIntent);
        //telefonun default olarak zil sesini alıp bildirim geldiğinde öttürtttümmm
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ibuilder.setSound(uri);


        Notification nnesne = ibuilder.getNotification();
        manager.notify(1, nnesne);
    }

}
