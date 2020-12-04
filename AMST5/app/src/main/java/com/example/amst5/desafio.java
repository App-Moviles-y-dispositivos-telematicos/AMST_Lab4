package com.example.amst5;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class desafio extends AppCompatActivity {

    private RequestQueue mQueue;
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);

        mQueue = Volley.newRequestQueue(this);
        revisarS();
    }
    public void revisarS(){
        Map<String, String> params = new HashMap();
        String usuario="estudiante";
        params.put("username", usuario);
        String password="stud3ntam5t";
        params.put("password", password);
        JSONObject parametros = new JSONObject(params);
        String postUrl = "https://amst-labx.herokuapp.com/db/nuevo-jwt";
        String postUrl2 = "https://amst-labx.herokuapp.com/api/sensores/4";

        JSONObject postData = new JSONObject();
        try {
            postData.put("id","4");
            postData.put("temperatura", "10°");

        } catch (JSONException e) {

        }
        JsonObjectRequest jsonObjectRequest;
        jsonObjectRequest = new JsonObjectRequest(Method.POST,postUrl,parametros ,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                new JsonObjectRequest(Method.POST, postUrl2,postData, new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        System.out.println(token);
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }

                });
                System.out.println(response);

            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "JWT " + token);
                System.out.println(token);
                return params;
            }
        };
        mQueue.add(jsonObjectRequest);
    }
}
