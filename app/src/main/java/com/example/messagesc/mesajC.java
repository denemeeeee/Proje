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
    String url="http://sadakatsizcpre.tr.ht/ekle.php";
    String url2="http://sadakatsizcpre.tr.ht/getir.php";
TextView tv;
EditText ad;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mesaj_sayfasi);

        tv=findViewById(R.id.txViewYazi);
        Button btn=findViewById(R.id.btnEkle);
        ad=findViewById(R.id.editDeger);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yaziyiGonder();

                         }
        });

    }

    private void yaziyiAl()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                tv.setText("");
                baslik="";
                alinanVerileriNesneyeDoldur(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Listeleme gerçekleştirilemedi",Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> deger =new HashMap<>();
                deger.get("eklenecekVeri");
                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);

    }
    private  void yaziyiGonder()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), ""+ad.getText().toString()+"eklendi", Toast.LENGTH_LONG).show();
                ad.setText("");
                yaziyiAl();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"İşlem gerçekleştirilemedi",Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> deger = new HashMap<>();
                deger.put("eklenecekVeri",ad.getText().toString());

                return deger;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequest(stringRequest);
    }
    String baslik="";
    private void alinanVerileriNesneyeDoldur(String string) {
        try {
            JSONObject response = new JSONObject(string);
            JSONArray arrYazilar = response.getJSONArray("donenVeriler");
            for (int i = 0; i < arrYazilar.length(); i++) {
                JSONObject yazi = arrYazilar.getJSONObject(i);
                baslik += yazi.getString("tag")+"\n";

            }
                tv.setText(baslik);





        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
