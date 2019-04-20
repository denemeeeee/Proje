package com.example.messagesc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class mesajC extends AppCompatActivity {
    String url = "http://sadakatsizcpre.tr.ht/ekle.php";
    String url2 = "http://sadakatsizcpre.tr.ht/getir.php";
    static TextView tv;
    static EditText ad;
    webservisislemleri wsi = new webservisislemleri();

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
                wsi.yaziyiGonder(url,getApplicationContext());
            }
        });

    }

}
