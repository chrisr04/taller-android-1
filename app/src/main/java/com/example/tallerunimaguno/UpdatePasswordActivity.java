package com.example.tallerunimaguno;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnUpdate;
    EditText txtPassword, txtConfrimPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        txtPassword = findViewById(R.id.txtPassword);
        txtConfrimPassword = findViewById(R.id.txtConfirmPassword);

        btnUpdate = findViewById(R.id.btnUpdate);

        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnUpdate:

                final String password = txtPassword.getText().toString();
                final String confirmPassword = txtConfrimPassword.getText().toString();

                if(!TextUtils.isEmpty(password)){

                    if(validatePassWord(password)){
                        if(!password.equals(confirmPassword)){
                            Toast.makeText(this, R.string.password_no_match, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        showAlertSuccess();
                    }else{
                        Toast.makeText(this, R.string.password_invalid, Toast.LENGTH_SHORT).show();
                    }




                }else{
                    Toast.makeText(this, R.string.err_campo_invalido, Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    public boolean validatePassWord(String password){
        return password.length() >= 6;
    }

    public void  showAlertSuccess(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("¡Contraseña actualizada con éxito!")
                .setMessage("Ahora puedes iniciar sesión")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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