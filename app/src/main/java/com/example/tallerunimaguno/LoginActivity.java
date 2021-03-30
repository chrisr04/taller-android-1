package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textEmail, textPassword;
    TextView txtForgot;
    CheckBox rememberMe, termsAndConditions;
    Button btnSignIn;
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        users.add(new User("christian@gmail.com","123456"));
        users.add(new User("julio@gmail.com","123456"));
        users.add(new User("alejandro@gmail.com","123456"));

        textEmail = (EditText) findViewById(R.id.txtEmail);
        textPassword = (EditText) findViewById(R.id.txtPassword);
        rememberMe = (CheckBox) findViewById(R.id.remember_me);
        txtForgot = findViewById(R.id.txtForgot);
        termsAndConditions = (CheckBox) findViewById(R.id.terms_and_conditions);

        checkCurrentUser();

        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        if(!termsAndConditions.isChecked()){
            btnSignIn.setEnabled(false);
        }

        btnSignIn.setOnClickListener(this);
        txtForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnSignIn:
                final String user = textEmail.getText().toString();
                final String password = textPassword.getText().toString();

                if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){

                    if(password.length()>=6){
                        if(isValidUser(user, password)){
                            if(rememberMe.isChecked()){
                                savePreferencesSignIn(user, password);
                            }else{
                                removePreferencesSignIn();
                            }

                            Toast.makeText(this,getString(R.string.se_ha_iniciado_sesion_como)+" \""+user+"\"", Toast.LENGTH_SHORT).show();
                            moveToOtherActivity(HomeActivity.class);
                        }else{
                            Toast.makeText(this, "Correo o contraseña invalida",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "La contraseña debe tener minimo 6 carácteres",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(this, R.string.err_campo_invalido,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtForgot:
                    moveToOtherActivity(ForgotPasswordActivity.class);
                break;
            case R.id.btnRegister:
                moveToOtherActivity(RegisterActivity.class);
                break;
        }
    }

    public void checkCurrentUser() {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login),Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(getString(R.string.pref_current_user), null);
        String password = sharedPreferences.getString(getString(R.string.pref_current_password), null);

        if(user!=null){
            textEmail.setText(user);
            textPassword.setText(password);
        }

        if (user!=null&&password!=null){
            Toast.makeText(this,getString(R.string.se_ha_iniciado_sesion_como)+" \""+user+"\"", Toast.LENGTH_SHORT).show();
            moveToOtherActivity(HomeActivity.class);
        }
    }

    public void moveToOtherActivity (Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        this.finish();
    }

    private void savePreferencesSignIn (String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.pref_current_user),email);
        editor.putString(getString(R.string.pref_current_password),password);
        editor.commit();
    }

    private void removePreferencesSignIn () {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.shared_pref_login),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.terms_and_conditions:
                if (checked){
                    btnSignIn.setEnabled(true);
                }else{
                    btnSignIn.setEnabled(false);
                }
                break;
        }
    }

    public boolean isValidUser(String email, String password){

        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if(user.email.equals(email) && user.password.equals(password)){
                return true;
            }
        }

        return false;
    }
}