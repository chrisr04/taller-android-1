package com.example.tallerunimaguno;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    ImageButton help, home, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        matematicas = findViewById(R.id.btnMath);
        help = findViewById(R.id.btnHelp);
        home = findViewById(R.id.btnHome);
        logout = findViewById(R.id.btnLogout);
        matematicas.setOnClickListener(this);
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
            case R.id.btnLogout:
                logOut();
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
}