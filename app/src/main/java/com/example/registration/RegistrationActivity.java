package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    private EditText etNewLogin;
    private EditText etNewPass;
    private EditText etNewPass2;
    private Button bBack;
    private Button bOk;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesCheck;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initElements();
        initListeners();
        sharedPreferences = getSharedPreferences(MainActivity.PREF_REG, MODE_PRIVATE);
        sharedPreferencesCheck = getSharedPreferences(MainActivity.PREF_CHECKER, MODE_PRIVATE);
    }

    private void initListeners() {

        bBack.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        bOk.setOnClickListener(this::registration);
    }

    private void registration(View view) {
        String userLogin = etNewLogin.getText().toString();
        String password = etNewPass.getText().toString();
        String password2 = etNewPass2.getText().toString();

        if (!userLogin.equals("") && !password.equals("") && password.equals(password2)){
            editor = sharedPreferencesCheck.edit();
            editor.putBoolean(MainActivity.KEY_ENTRY, true);
            editor.apply();

            editor = sharedPreferences.edit();
            editor.putString(MainActivity.KEY_LOGIN, userLogin);
            editor.putString(MainActivity.KEY_PASSWORD, password);
            editor.apply();
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }else {
            Toast.makeText(this, "wrong input", Toast.LENGTH_SHORT).show();
        }

    }

    private void initElements() {
        etNewLogin = findViewById(R.id.etNewLogin);
        etNewPass = findViewById(R.id.etNewPass);
        etNewPass2 = findViewById(R.id.etNewPass2);
        bBack = findViewById(R.id.bBack);
        bOk = findViewById(R.id.bOk);
    }
}