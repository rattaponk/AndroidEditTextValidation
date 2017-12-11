package com.example.navadroid.androidedittextvalidation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Option1Activity extends AppCompatActivity{

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);
        bindView();
        initView();
    }

    private void bindView() {
        etName = (EditText) findViewById(R.id.et_name1);
        etPwd = (EditText) findViewById(R.id.et_pwd1);
        etEmail = (EditText) findViewById(R.id.et_email1);
        etPhone = (EditText) findViewById(R.id.et_phone1);
    }

    private void initView() {
        findViewById(R.id.btn_validate1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateEditText() && validateName(etName.getText().toString())
                        && validatePassword(etPwd.getText().toString()) && validateEmail(etEmail.getText().toString())) {
                    Toast.makeText(Option1Activity.this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
                    // SnackBar?
                }

            }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This can be ignored
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This can be ignored
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if(!validateName(text)) // OR validation can be specific (only for this EditText)
                    etName.setError("Invalid name");
            }
        });

        etPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: add your Password validation here
                String text = s.toString();
                if(!validatePassword(text))
                    etPwd.setError("Require more 8 digits.");
            }
        });

//        etPhone.addTextChangedListener(...);
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString();
                if(!validateEmail(text))
                    etEmail.setError("Invalid email");
            }
        });
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
