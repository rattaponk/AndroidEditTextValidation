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

public class Option2Activity extends AppCompatActivity implements TextWatcher, View.OnClickListener{

    private EditText etName;
    private EditText etPwd;
    private EditText etEmail;
    private EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option2);
        bindView();
        initView();
    }

    private void bindView(){
        etName = (EditText) findViewById(R.id.et_name2);
        etPwd = (EditText) findViewById(R.id.et_pwd2);
        etEmail = (EditText) findViewById(R.id.et_email2);
        etPhone = (EditText) findViewById(R.id.et_phone2);
    }

    private void initView(){
        // OnClickListener
        findViewById(R.id.btn_validate2).setOnClickListener(this);

        // TextChangedListener
        etName.addTextChangedListener(this);
        etPwd.addTextChangedListener(this);
        etEmail.addTextChangedListener(this);
        etPhone.addTextChangedListener(this);
    }

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
//        validateEditText();
        if(!validateName(etName.getText().toString()))
            etName.setError("Invalid name");
        if(!validatePassword(etPwd.getText().toString()))
            etPwd.setError("Require more 8 digits");
        if(!validateEmail(etEmail.getText().toString()))
            etEmail.setError("Invalid email");
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_validate2) {
            if (validateEditText() && validateName(etName.getText().toString())
                    && validatePassword(etPwd.getText().toString()) && validateEmail(etEmail.getText().toString())) {
                Toast.makeText(this, "Okay. You are good to go.", Toast.LENGTH_SHORT).show();
            }
        }
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
