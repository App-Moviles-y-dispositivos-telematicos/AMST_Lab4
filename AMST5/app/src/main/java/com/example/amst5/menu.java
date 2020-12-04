package com.example.amst5;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;

public class menu extends AppCompatActivity {

    String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent login = getIntent();
        this.token = (String)login.getExtras().get("token");
    }

    public void Salir(View v){
        this.finish();
        System.exit(0);
    }
    public void desafioT (View v){
        Intent desafio1 = new Intent(getBaseContext(), desafio.class);
        desafio1.putExtra("token", token);
        startActivity(desafio1);
    }
    public void revisarSensores(View v){
        Intent red_sensores = new Intent(getBaseContext(), RedSensores.class);
        red_sensores.putExtra("token", token);
        startActivity(red_sensores);
    }

}