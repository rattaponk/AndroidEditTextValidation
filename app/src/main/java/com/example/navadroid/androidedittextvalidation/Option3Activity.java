package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                if (validateEditText() && validateName(etName.getText().toString())
                        && validatePassword(etPwd.getText().toString()) && validateEmail(etEmail.getText().toString())){
                    Toast.makeText(Option3Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // TextChangedListener
        etName.addTextChangedListener(new TextValidator(etName) {
            @Override
            public void validate(TextView textView, String text) {
                if(!validateName(etName.getText().toString()))
                    etName.setError("Invalid name");
            }
        });

        etPwd.addTextChangedListener(new TextValidator(etPwd) {
            @Override
            public void validate(TextView textView, String text) {
                // TODO: add your Password validation here
                if(!validatePassword(etPwd.getText().toString()))
                    etPwd.setError("Require more 8 digits");
            }
        });

        etEmail.addTextChangedListener(new TextValidator(etPwd) {
            @Override
            public void validate(TextView textView, String text) {
                if(!validateEmail(etEmail.getText().toString()))
                    etEmail.setError("Invalid email");
            }
        });;
        //etPhone.addTextChangedListener(...);
    }


    // To validate all EditTexts
    private boolean validateEditText() {
        boolean isValidated = true;
        if (etName.getText().toString().length() == 0) {
            etName.setError("Name Required");
            isValidated = false;
        }
        // TODO: add your EditText validation here
        if (etPwd.getText().toString().length() == 0) {
            etPwd.setError("Password Required");
            isValidated = false;
        }

        if (etEmail.getText().toString().length() == 0) {
            etEmail.setError("Email Required");
            isValidated = false;
        }

        if (etPhone.getText().toString().length() == 0) {
            etPhone.setError("Phone Required");
            isValidated = false;
        }

        return isValidated;
    }

    private boolean validateName(String name) {
        String VALID_PASSWORD_REGEX = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(VALID_PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public boolean validatePassword(String password) {
        String VALID_PASSWORD_REGEX = "^(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(VALID_PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean validateEmail(String email) {
        String VALID_EMAIL_ADDRESS_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(VALID_EMAIL_ADDRESS_REGEX, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}
