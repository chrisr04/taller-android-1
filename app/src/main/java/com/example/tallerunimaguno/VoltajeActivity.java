package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class VoltajeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button calcularVoltaje, borrarVoltaje;
    EditText amperaje, r1, r2, r3;
    CheckBox paralelo;
    String[] resis = {"","2","3"};
    Spinner spnNresis;
    String resisElegidaSpinner;
    float rt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltaje);
    //inicia spinner
        spnNresis = (Spinner) findViewById(R.id.spinnerResistencias);
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, resis);// se le asigna el vector de arriba
        spnNresis.setAdapter(adaptador);
        //ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.opcionesResistencias, android.R.layout.simple_spinner_item);
        //spnNresistencias.setAdapter(adaptador);
    //termina asignacion para spinner
        spnNresis.setOnItemSelectedListener(this); // para que se mantenga escuchando desde el celular el Spinner y ejecute la funcion de abajo para leer lo que se elija


        amperaje = findViewById(R.id.editTextAmperaje);
        r1 = findViewById(R.id.editTextResistencia1);
        r2 = findViewById(R.id.editTextResistencia2);
        r3 = findViewById(R.id.editTextResistencia3);

        paralelo = (CheckBox) findViewById(R.id.checkBoxResistencias);

        calcularVoltaje = findViewById(R.id.btnCalcularVoltaje);
        borrarVoltaje = findViewById(R.id.btnBorrarVoltaje);

        calcularVoltaje.setOnClickListener(this);
        borrarVoltaje.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCalcularVoltaje:
                if(!amperaje.getText().toString().isEmpty() && !TextUtils.isEmpty(resisElegidaSpinner) &&
                    !r1.getText().toString().isEmpty() && !r2.getText().toString().isEmpty()){ // comprobante de editext no vacio para numeros y letrasm dependiendo del tipo de editText
                         // el r3 no iba aqui porque si puede ir vacio cuando sean 2 resis
                         // ahora que entró y comprobó que todas excepto la 3 tienen un valor
                        if (resisElegidaSpinner=="3" && r3.getText().toString().isEmpty()){ // comprueba si el numero es 3 y si esta vacio el r3 entonces faltan datos
                             Toast.makeText(this, "Faltan datos",Toast.LENGTH_SHORT).show();
                        }else{ // sino entonces es un 3 con todos los datos o un r2 que ya se sabia que tenia los 2 datos y el resto
                            //calcular codigo dependiendo de las 2 posibles formulas para R y si es serie o paralelo
                            // convertimos primero los valores leidos a float porque estaban en editText
                            float r1f = Float.valueOf(r1.getText().toString());
                            float r2f = Float.valueOf(r2.getText().toString());
                            //float r3f = Float.valueOf(r3.getText().toString()); // se me cierra la app cuando solo hay 2, por eso debe ir dentro de las opciones de cuando hay 3
                            float amperajef = Float.valueOf(amperaje.getText().toString());

                            if (resisElegidaSpinner=="2" && !paralelo.isChecked()){//Serie
                                rt = r1f+r2f;
                                //Toast.makeText(this, ""+(rt), Toast.LENGTH_SHORT).show(); error al imprimir numero
                                //Toast.makeText(getApplicationContext(),""+(rt), Toast.LENGTH_LONG).show();
                            }
                            if (resisElegidaSpinner=="2" && paralelo.isChecked()){//Paralelo
                                rt = (1/(1/r1f+1/r2f));
                                //Toast.makeText(this, ""+(rt), Toast.LENGTH_SHORT).show();
                            }
                            if (resisElegidaSpinner=="3" && !paralelo.isChecked()){//Serie
                                float r3f = Float.valueOf(r3.getText().toString());
                                rt = r1f + r3f + r2f;
                                //Toast.makeText(this, ""+(rt), Toast.LENGTH_SHORT).show();
                            }
                            if (resisElegidaSpinner=="3" && paralelo.isChecked()){//Paralelo
                                float r3f = Float.valueOf(r3.getText().toString());
                                rt = (1/(1/r1f+1/r2f+1/r3f));
                                //Toast.makeText(this, ""+(rt), Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(this, "El Voltaje es :  "+( amperajef * rt )+" V", Toast.LENGTH_SHORT).show();
                            //Toast.makeText(this, "calculando", Toast.LENGTH_SHORT).show();
                        }
                }else {
                    Toast.makeText(this, "Faltan datos",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnBorrarVoltaje:
                    //Toast.makeText(this, "presionaste el boton borrar", Toast.LENGTH_SHORT).show();
                    borrar();
                break;
        }
    }
    public void borrar(){
        r1.setText("");
        r2.setText("");
        r3.setText("");
        amperaje.setText("");
        spnNresis.setSelection(0);// seleccionar la opcion del vector 0 del spinner
        //Toast.makeText(this, resisElegidaSpinner, Toast.LENGTH_SHORT).show(); // para probar si al cambiar su seleccion cambia el valor de la variable o toca hacerlo tambien
        resisElegidaSpinner=""; // porque al mover el spiner a blanco no cambia la variable, por ello hacerlo tambien
        paralelo.setChecked(false); //resetear el checkbox
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //resisElegidaSpinner.setText(parent.getItemAtPosition(position).toString()); // esta es solo si asignaba a un EditText
        //Toast.makeText(this, ""+resisElegidaSpinner, Toast.LENGTH_SHORT).show();
        resisElegidaSpinner=parent.getItemAtPosition(position).toString(); // cada vez que se seleccione una nueva opcion en el spinner se le asigna a esta variable, y al inicio tambien se ejecuta
        //Toast.makeText(this, resisElegidaSpinner, Toast.LENGTH_SHORT).show();
        //ahora los apagados y encendidos de loseditText como dice el enunciado
        if (resisElegidaSpinner==""){ //es necesario para cuando vuelva a ponerse 0, por esta ya no es necesari dejarlo desabilitado en la interfaz porque se ejecuta cada que uno entra pero ya quedó así
            r1.setEnabled(false);
            r2.setEnabled(false);
            r3.setEnabled(false);
            //Toast.makeText(this, resisElegidaSpinner, Toast.LENGTH_SHORT).show();
        }
        if (resisElegidaSpinner=="2"){
            r1.setEnabled(true);
            r2.setEnabled(true);
            r3.setEnabled(false);
            //Toast.makeText(this, resisElegidaSpinner, Toast.LENGTH_SHORT).show();
        }
        if (resisElegidaSpinner=="3"){
            r1.setEnabled(true);
            r2.setEnabled(true);
            r3.setEnabled(true);
            //Toast.makeText(this, resisElegidaSpinner, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {//funcion par con la de arriba para los Spinner

    }
}