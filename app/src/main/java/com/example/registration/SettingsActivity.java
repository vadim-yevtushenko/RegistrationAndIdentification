package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity implements Keys{
    private EditText etOldPass;
    private EditText etNewLogin;
    private EditText etNewPass;
    private EditText etNewPass2;
    private Button bBack;
    private Button bOk;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initElements();
        initListeners();
        sharedPreferences = getSharedPreferences(PREF_REG, MODE_PRIVATE);
    }

    private void initListeners() {

        bBack.setOnClickListener(v -> {
            finish();
        });
        bOk.setOnClickListener(this::registration);
    }

    private void registration(View view) {
        String oldPass = etOldPass.getText().toString();
        String oldPass2 = sharedPreferences.getString(KEY_PASSWORD, "");
        String userLogin = etNewLogin.getText().toString();
        String password = etNewPass.getText().toString();
        String password2 = etNewPass2.getText().toString();
        if (oldPass.equals(oldPass2)){
            editor = sharedPreferences.edit();
            if (!userLogin.equals("")){
                editor.putString(KEY_LOGIN, userLogin);
            }
            if (!password.equals("")){
                if (password.equals(password2)){
                    editor.putString(KEY_PASSWORD, password);
                }else {
                    Toast.makeText(this, "passwords not same", Toast.LENGTH_SHORT).show();
                }
            }
            editor.apply();
            finish();
        }else {
            Toast.makeText(this, "wrong old password", Toast.LENGTH_SHORT).show();
        }



    }

    private void initElements() {
        etOldPass = findViewById(R.id.etOldPass);
        etNewLogin = findViewById(R.id.etNewLogin);
        etNewPass = findViewById(R.id.etNewPass);
        etNewPass2 = findViewById(R.id.etNewPass2);
        bBack = findViewById(R.id.bBack);
        bOk = findViewById(R.id.bSignUp);
    }
}