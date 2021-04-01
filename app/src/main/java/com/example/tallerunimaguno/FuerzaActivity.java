package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class FuerzaActivity extends AppCompatActivity implements View.OnClickListener  {

    Button calcularFuerza, borrarFuerza;
    EditText masa, aceleracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza);


        masa = findViewById(R.id.editTextMasa);
        aceleracion = findViewById(R.id.editTextAceleracion);

        calcularFuerza = findViewById(R.id.btnCalcularFuerza);
        borrarFuerza = findViewById(R.id.btnBorrarFuerza);

        calcularFuerza.setOnClickListener(this);
        borrarFuerza.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCalcularFuerza:
                if( !masa.getText().toString().isEmpty() && !aceleracion.getText().toString().isEmpty()){ // comprobante de editext no vacio para numeros y letrasm dependiendo del tipo de editText
                        float masaf = Float.valueOf(masa.getText().toString());
                        float aceleracionf = Float.valueOf(aceleracion.getText().toString());

                        Toast.makeText(this, "La fuerza necesaria para mover es :  "+( masaf * aceleracionf )+" N", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(this, "calculando", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "Faltan datos",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBorrarFuerza:
                //Toast.makeText(this, "presionaste el boton borrar", Toast.LENGTH_SHORT).show();
                borrar();
                break;
        }
    }
    public void borrar(){
        masa.setText("");
        aceleracion.setText("");
    }
}