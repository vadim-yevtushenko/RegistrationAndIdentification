package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etLogin;
    private EditText etPassword;
    private Button bIdentify;
    private Button bRegistration;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesCheck;
    private SharedPreferences.Editor editor;
    public static final String PREF_REG = "reg";
    public static final String PREF_CHECKER = "checker";
    public static final String KEY_ENTRY = "entry";
    public static final String KEY_LOGIN = "log";
    public static final String KEY_PASSWORD = "pass";
    public static boolean isEntered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesCheck = getSharedPreferences(PREF_CHECKER, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(PREF_REG, MODE_PRIVATE);
        isEntered = sharedPreferencesCheck.getBoolean(KEY_ENTRY, false);
        if (isEntered){
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }
        initElements();
        initListeners();

    }

    private void initListeners() {
        bIdentify.setOnClickListener(this::identify);
        bRegistration.setOnClickListener(this::registration);
    }

    private void registration(View view) {
        startActivity(new Intent(this, RegistrationActivity.class));
        finish();
    }

    private void identify(View view) {
        String userLogin = etLogin.getText().toString();
        String password = etPassword.getText().toString();
        String userLogin1 = sharedPreferences.getString(KEY_LOGIN, "");
        String password1 = sharedPreferences.getString(KEY_PASSWORD, "");
        if (userLogin.equals(userLogin1) && password.equals(password1)){
            editor = sharedPreferencesCheck.edit();
            editor.putBoolean(KEY_ENTRY, true);
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }else {
            Toast.makeText(this, "wrong login or password", Toast.LENGTH_SHORT).show();
        }

    }

    private void initElements() {
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        bIdentify = findViewById(R.id.bIdetify);
        bRegistration = findViewById(R.id.bRegistration);
    }
}