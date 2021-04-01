package com.example.tallerunimaguno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button matematicas;
    Button fisica; // A
    ImageButton help, home, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        matematicas = findViewById(R.id.btnMath);
        fisica = findViewById(R.id.btnPhysic); // A
        help = findViewById(R.id.btnHelp);
        home = findViewById(R.id.btnHome);
        logout = findViewById(R.id.btnLogout);
        matematicas.setOnClickListener(this);
        fisica.setOnClickListener(this); // A
        help.setOnClickListener(this);
        home.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.mnugeometria:
            //    Toast.makeText(getApplicationContext(), "MENU GEOMETRIA", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), GeometriaActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMath:
                Intent i = new Intent(getApplicationContext(),MatematicasActivity.class);
                startActivity(i);
                break;
            case R.id.btnPhysic: // A
                Intent j = new Intent(getApplicationContext(),FisicaActivity.class); // A
                startActivity(j); // A
                break; // A
            case R.id.btnLogout:
                confirmLogout();
                break;
            case R.id.btnHelp:
                showHelpAlert();
                break;
        }
    }

    public void logOut() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_current_password), null);
        editor.apply();
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        Toast.makeText(this, R.string.se_ha_cerrado_la_sesion, Toast.LENGTH_SHORT).show();
        startActivity(intent);
        this.finish();
    }

    public void  showHelpAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
            .setTitle("Ayuda")
            .setMessage("APP v1 Realizado por: \nChristian Rodriguez\nJulio Rubio\nAlejandro Polo")
            .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            })
            .show();
    }

    public void  confirmLogout(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Cerrar Sesión")
                .setMessage("¿Deseas cerrar sesión?")
                .setPositiveButton("Si, salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        logOut();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}