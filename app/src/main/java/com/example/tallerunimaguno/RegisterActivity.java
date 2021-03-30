package com.example.tallerunimaguno;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity
                              implements View.OnClickListener,
                              AdapterView.OnItemSelectedListener {

    EditText txtFirstname;
    EditText txtLastname;
    EditText txtEmail;
    EditText txtPassword;
    EditText txtGenre;

    String[] genre = { "", "Masculino", "Femenino"};
    Spinner genreSpn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtFirstname = findViewById(R.id.txtFirstname);
        txtLastname = findViewById(R.id.txtLastname);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtGenre = findViewById(R.id.txtGenre);
        txtGenre.setOnClickListener(this);

        genreSpn = findViewById(R.id.genre);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, genre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpn.setAdapter(adapter);
        genreSpn.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtGenre:
                genreSpn.performClick();
                break;
            case R.id.btnRegister:

                final String firstname = txtFirstname.getText().toString();
                final String lastname = txtLastname.getText().toString();
                final String email = txtEmail.getText().toString();
                final String password = txtPassword.getText().toString();
                final String genre = txtGenre.getText().toString();

                if(!TextUtils.isEmpty(firstname) && !TextUtils.isEmpty(lastname) &&
                        !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) &&
                        !TextUtils.isEmpty(genre)){

                    if(!validateEmail(email)){
                        Toast.makeText(this, R.string.email_invalid,Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(!validatePassWord(password)){
                        Toast.makeText(this, R.string.password_invalid,Toast.LENGTH_SHORT).show();
                        return;
                    }

                    showAlertSuccess();

                }else {
                    Toast.makeText(this, R.string.err_campo_invalido,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnSignIn:
                moveToOtherActivity(LoginActivity.class);
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        txtGenre.setText(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean validateEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean validatePassWord(String password){
        return password.length() >= 6;
    }

    public void  showAlertSuccess(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
            .setTitle("¡Registro realizado con éxito!")
            .setMessage("Gracias por registrarte en nuestra app")
            .setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    moveToOtherActivity(LoginActivity.class);
                }
            })
            .show();
    }

    public void moveToOtherActivity (Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        this.finish();
    }
}