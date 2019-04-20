package com.example.messagesc;

import android.content.Context;
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

public class webservisislemleri {
    Context context;
    String url;
    String url2;

    public webservisislemleri(Context cont, String url, String url2) {
        this.context = cont;
        this.url = url;
        this.url2 = url2;
    }

    private void yaziyiAl() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mesajC.tv.setText("");
                baslik = "";
                alinanVerileriNesneyeDoldur(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Listeleme gerçekleştirilemedi", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> deger = new HashMap<>();
                deger.get("eklenecekVeri");
                return deger;
            }
        };
        MySingleton.getInstance(context).addToRequest(stringRequest);

    }

    public void yaziyiGonder() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String url2 = "http://sadakatsizcpre.tr.ht/getir.php";
                Toast.makeText(context, "" + mesajC.ad.getText().toString() + "eklendi", Toast.LENGTH_LONG).show();
                mesajC.ad.setText("");
                yaziyiAl();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "İşlem gerçekleştirilemedi", Toast.LENGTH_SHORT).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> deger = new HashMap<>();
                deger.put("eklenecekVeri", mesajC.ad.getText().toString());

                return deger;
            }
        };
        MySingleton.getInstance(context).addToRequest(stringRequest);
    }

    String baslik = "";

    private void alinanVerileriNesneyeDoldur(String string) {
        try {
            JSONObject response = new JSONObject(string);
            JSONArray arrYazilar = response.getJSONArray("donenVeriler");
            for (int i = 0; i < arrYazilar.length(); i++) {
                JSONObject yazi = arrYazilar.getJSONObject(i);
                baslik += yazi.getString("tag") + "\n";

            }
            mesajC.tv.setText(baslik);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
