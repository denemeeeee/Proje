package com.example.messagesc;

import android.content.Context;
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
            }
        });

    }

}
