package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesCheck;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        tvGreeting = findViewById(R.id.tvGreeting);
        sharedPreferences = getSharedPreferences(MainActivity.PREF_REG, MODE_PRIVATE);
        sharedPreferencesCheck = getSharedPreferences(MainActivity.PREF_CHECKER, MODE_PRIVATE);
        showGreeting();
    }

    private void showGreeting() {
        String userLogin = sharedPreferences.getString(MainActivity.KEY_LOGIN, "user");
        tvGreeting.setText("Hello, " + userLogin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemProfile){
            startActivity(new Intent(this, SettingsActivity.class));
        }else if(id == R.id.itemExitFromApp){
            selectExit();
        }else if (id == R.id.itemExitFromProfile){
            changeEntry();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void selectExit() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.ad_title)
                .setMessage(R.string.ad_message)
                .setPositiveButton(R.string.ad_positive, (dialog, which) -> {
                    changeEntry();
                    finish();
                })
                .setNegativeButton(R.string.ad_negative, (dialog, which) -> {
                    finish();
                })
                .setNeutralButton(R.string.ad_neutral, (dialog, which) -> {

                });
        alertDialog.show();
    }

    private void changeEntry() {
        editor = sharedPreferencesCheck.edit();
        editor.putBoolean(MainActivity.KEY_ENTRY, false);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        showGreeting();
        super.onResume();
    }
}