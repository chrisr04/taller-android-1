package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FisicaActivity extends AppCompatActivity implements View.OnClickListener  {

    Button fuerza, velocidad, voltaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisica);

        fuerza = findViewById(R.id.btnFuerza);
        velocidad = findViewById(R.id.btnVelocidad);
        voltaje =  findViewById(R.id.btnVoltaje);

        fuerza.setOnClickListener(this);
        velocidad.setOnClickListener(this);
        voltaje.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnFuerza:
                Intent i = new Intent(getApplicationContext(),FuerzaActivity.class);
                startActivity(i);
                break;
            case R.id.btnVelocidad:
                Intent j = new Intent(getApplicationContext(),VelocidadActivity.class);
                startActivity(j);
                break;
            case R.id.btnVoltaje:
                Intent k = new Intent(getApplicationContext(),VoltajeActivity.class);
                startActivity(k);
                break;
        }
    }
}