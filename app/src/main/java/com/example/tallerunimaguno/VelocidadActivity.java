package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VelocidadActivity extends AppCompatActivity implements View.OnClickListener  {

    Button calcularVelocidad, borrarVelocidad;
    EditText distancia, tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidad);


        distancia = findViewById(R.id.editTextDistancia);
        tiempo = findViewById(R.id.editTextTiempo);

        calcularVelocidad = findViewById(R.id.btnCalcularVelocidad);
        borrarVelocidad = findViewById(R.id.btnBorrarVelocidad);

        calcularVelocidad.setOnClickListener(this);
        borrarVelocidad.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCalcularVelocidad:
                if( !distancia.getText().toString().isEmpty() && !tiempo.getText().toString().isEmpty()){ // comprobante de editext no vacio para numeros y letrasm dependiendo del tipo de editText
                    float distanciaf = Float.valueOf(distancia.getText().toString());
                    float tiempof = Float.valueOf(tiempo.getText().toString());

                    Toast.makeText(this, "La velocidad empleada fue :  "+( distanciaf / tiempof )+" m/s", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this, "calculando", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Faltan datos",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBorrarVelocidad:
                //Toast.makeText(this, "presionaste el boton borrar", Toast.LENGTH_SHORT).show();
                borrar();
                break;
        }
    }
    public void borrar(){
        distancia.setText("");
        tiempo.setText("");
    }
}