package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Option3Activity extends AppCompatActivity {

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option3);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name3);
        etPwd = (EditText) findViewById(R.id.et_pwd3);
        etEmail = (EditText) findViewById(R.id.et_email3);
        etPhone = (EditText) findViewById(R.id.et_phone3);
    }

    private void initView(){
        // OnClickListener
        findViewById(R.id.btn_validate3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateEditText()){
                    Toast.makeText(Option3Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TextChangedListener
        etName.addTextChangedListener(new TextValidator(etName) {
            @Override
            public void validate(TextView textView, String text) {
                if (etName.getText().toString().length() == 0) {
                    etName.setError("Required");
                }
            }
        });

        etPwd.addTextChangedListener(new TextValidator(etPwd) {
            @Override
            public void validate(TextView textView, String text) {
                // TODO: add your Password validation here
                if (etPwd.getText().toString().length() < 6) {
                    etPwd.setError("Required");
                }
            }
        });

        //etEmail.addTextChangedListener(...);
        //etPhone.addTextChangedListener(...);
    }


    // To validate all EditTexts
    private boolean validateEditText(){
        boolean isValidated = true;
        if (etName.getText().toString().length() == 0) {
            etName.setError("Required");
            isValidated = false;
        }
        // TODO: add your EditText validation here
        if (etPwd.getText().toString().length() < 6) {
            etPwd.setError("Required");
            isValidated = false;
        }

        return isValidated;
    }
}
