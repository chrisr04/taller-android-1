package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    int[] validCodes = {797360, 323344, 423231, 232434};
    Button btnSend, btnValidate;
    EditText txtEmail, txtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        btnSend = findViewById(R.id.btnSend);
        btnValidate = findViewById(R.id.btnValidate);
        txtEmail = findViewById(R.id.txtEmail);
        txtCode = findViewById(R.id.txtCode);

        btnSend.setOnClickListener(this);
        btnValidate.setOnClickListener(this);
    }

    public boolean validateEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean validateCode(int code){

        for (int i = 0; i < validCodes.length; i++){
            if(code == validCodes[i]){
                return  true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSend:
                final String email = txtEmail.getText().toString();

                if(!TextUtils.isEmpty(email)){

                    if(!validateEmail(email)){
                        Toast.makeText(this, R.string.email_invalid, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sendCode();
                }else{
                    Toast.makeText(this, R.string.err_campo_invalido, Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btnValidate:

                final String code = txtCode.getText().toString();

                if(!TextUtils.isEmpty(code)){

                    if(!validateCode(Integer.parseInt(code))){
                        Toast.makeText(this, R.string.code_invalid, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    moveToOtherActivity(UpdatePasswordActivity.class);

                }else{
                    Toast.makeText(this, R.string.err_campo_invalido, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    public void sendCode(){
        Random rand = new Random();
        int i = rand.nextInt(4);
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{txtEmail.getText().toString()});
        email.putExtra(Intent.EXTRA_SUBJECT, "Código de confirmación");
        email.putExtra(Intent.EXTRA_TEXT, "El codigo de confirmación es: "+ validCodes[i]);
        email.setType("message/rfc822");
        try {
            startActivity(Intent.createChooser(email, "Enviar código desde:"));
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "No hay ningun cliente de correo instalado", Toast.LENGTH_SHORT).show();
        }
    }

    public void moveToOtherActivity (Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        startActivity(intent);
        this.finish();
    }
}