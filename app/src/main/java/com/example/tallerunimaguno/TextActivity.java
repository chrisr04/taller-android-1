package com.example.tallerunimaguno;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class TextActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox boldCheck, italicCheck, underCheck;
    EditText textInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        textInput = findViewById(R.id.txt);
        boldCheck = findViewById(R.id.boldCheck);
        italicCheck = findViewById(R.id.italicCheck);
        underCheck = findViewById(R.id.underlineCheck);
        boldCheck.setOnClickListener(this);
        italicCheck.setOnClickListener(this);
        underCheck.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        verifyTypeface();
    }

    public void verifyTypeface(){
        if(boldCheck.isChecked() && !italicCheck.isChecked() && !underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.BOLD);
            textInput.getPaint().setUnderlineText(false);
            textInput.setText(textInput.getText());
        }else if(!boldCheck.isChecked() && italicCheck.isChecked() && !underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.ITALIC);
            textInput.getPaint().setUnderlineText(false);
            textInput.setText(textInput.getText());
        }else if(boldCheck.isChecked() && italicCheck.isChecked() && !underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.BOLD_ITALIC);
            textInput.getPaint().setUnderlineText(false);
            textInput.setText(textInput.getText());
        }else if(!boldCheck.isChecked() && !italicCheck.isChecked() && underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.NORMAL);
            textInput.getPaint().setUnderlineText(true);
            textInput.setText(textInput.getText());
        }else if(!boldCheck.isChecked() && italicCheck.isChecked() && underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.ITALIC);
            textInput.getPaint().setUnderlineText(true);
            textInput.setText(textInput.getText());
        }else if(boldCheck.isChecked() && !italicCheck.isChecked() && underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.BOLD);
            textInput.getPaint().setUnderlineText(true);
            textInput.setText(textInput.getText());
        }else if(boldCheck.isChecked() && italicCheck.isChecked() && underCheck.isChecked()){
            textInput.setTypeface(null, Typeface.BOLD_ITALIC);
            textInput.getPaint().setUnderlineText(true);
            textInput.setText(textInput.getText());
        }else{
            textInput.setTypeface(null, Typeface.NORMAL);
            textInput.getPaint().setUnderlineText(false);
            textInput.setText(textInput.getText());
        }
    }
}