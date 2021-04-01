package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GeometriaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    Button calculargeoBtn1;
    EditText Etpuntox1, Etpuntox2, Etpuntoy1, Etpuntoy2;
    String eleccion;
    int pendiente;
    double distancia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometria);

        Etpuntox1 = (EditText) findViewById(R.id.puntox1);
        Etpuntox2 = (EditText) findViewById(R.id.puntox2);
        Etpuntoy1 = (EditText) findViewById(R.id.puntoy1);
        Etpuntoy2 = (EditText) findViewById(R.id.puntoy2);

        //Etpuntox1.getText().toString();
        //Etpuntox2.getText().toString();
        //Etpuntoy1.getText().toString();
        //Etpuntoy2.getText().toString();

        calculargeoBtn1 = findViewById(R.id.calculargeoBtn);
        calculargeoBtn1.setOnClickListener(this);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(GeometriaActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.opciones));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(this);
        //calculargeoBtn1=findViewById(R.id.calculargeoBtn);
        //calculargeoBtn1.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.calculargeoBtn:
                String value1 = Etpuntox1.getText().toString();
                String value2 = Etpuntox2.getText().toString();
                String value3 = Etpuntoy1.getText().toString();
                String value4 = Etpuntoy2.getText().toString();

                if (TextUtils.isEmpty(value1) || TextUtils.isEmpty(value2) || TextUtils.isEmpty(value3) || TextUtils.isEmpty(value4)) {
                    Toast.makeText(this, "Ingrese un Numero", Toast.LENGTH_SHORT).show();
                }

                if(eleccion.equals("Cuadrante")) {

                    if ((Integer.parseInt(value1) > 0) && (Integer.parseInt(value3) > 0)) {
                        Toast.makeText(this, "La cordenada X1 y Y1 pertenece al primer cuadrante", Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(value2) > 0) && (Integer.parseInt(value4) > 0)) {
                        Toast.makeText(this, "La cordenada X2 y Y2 pertenece al primer cuadrante", Toast.LENGTH_SHORT).show();// primer cuadrante
                    }
                    if ((Integer.parseInt(value1) < 0) && (Integer.parseInt(value3) > 0)) {
                        Toast.makeText(this, "La cordenada X1 y Y1 pertenece al segundo cuadrante", Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(value2) < 0) && (Integer.parseInt(value4) > 0)) {
                        Toast.makeText(this, "La cordenada X2 y Y2 pertenece al segundo cuadrante", Toast.LENGTH_SHORT).show();// segundo cuadrante
                    }
                    if ((Integer.parseInt(value1) < 0) && (Integer.parseInt(value3) < 0)) {
                        Toast.makeText(this, "La cordenada X1 y Y1 pertenece al tercer cuadrante", Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(value2) < 0) && (Integer.parseInt(value4) < 0)) {
                        Toast.makeText(this, "La cordenada X2 y Y2 pertenece al tercer cuadrante", Toast.LENGTH_SHORT).show();// tercer cuadrante
                    }
                    if ((Integer.parseInt(value1) > 0) && (Integer.parseInt(value3) < 0)) {
                        Toast.makeText(this, "La cordenada X1 y Y1 pertenece al cuarto cuadrante", Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(value1) > 0) && (Integer.parseInt(value3) < 0)) {
                        Toast.makeText(this, "La cordenada X2 y Y2 pertenece al cuarto cuadrante", Toast.LENGTH_SHORT).show();// cuarto cuadrante
                    }

                }  else if(eleccion.equals("Pendiente"))  {

                    pendiente=((Integer.parseInt(value4))-(Integer.parseInt(value3)))/((Integer.parseInt(value2))-(Integer.parseInt(value1)));
                    Toast.makeText(this, "La pendiente entre los 2 puntos es: "+ pendiente, Toast.LENGTH_SHORT).show();

                } else if(eleccion.equals("Distancia")) {
                    double XD = ((Integer.parseInt(value2))-(Integer.parseInt(value1)));
                    double XY = ((Integer.parseInt(value4))-(Integer.parseInt(value3)));
                    distancia = Math.sqrt((Math.pow(XD,2))+(Math.pow(XY,2)));
                    Toast.makeText(this, "La distancia entre los 2 puntos es: "+ distancia, Toast.LENGTH_SHORT).show();
                }

                break;
        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        eleccion=parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}



