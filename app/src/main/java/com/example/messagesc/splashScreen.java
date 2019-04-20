package com.example.messagesc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class splashScreen extends AppCompatActivity {
        public boolean internetVarmi(final Context context) {
            final ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivity.getActiveNetworkInfo() != null && connectivity.getActiveNetworkInfo().isConnected();
        }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);
        if (internetVarmi(this)) {
            Thread splashScreen;
            splashScreen = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (this) {
                            wait(3000);
                        }
                    } catch (InterruptedException ex) {

                    } finally {
                        startActivity(new Intent(getApplicationContext(), mesajC.class));
                        finish();
                    }
                }
            });
            splashScreen.start();
        } else {
            AlertDialog alert = new AlertDialog.Builder(this).create();
            alert.setTitle("Bağlantı hatası");
            alert.setMessage("Lütfen internet bağlantınızı kontrol ediniz");
            alert.setButton(DialogInterface.BUTTON_NEUTRAL, "Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                    dialog.dismiss();
                }
            });
            alert.show();
        }

    }
}
