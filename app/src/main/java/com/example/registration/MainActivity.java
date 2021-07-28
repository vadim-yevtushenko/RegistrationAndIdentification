package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Keys {


    private TextView tvSignUp;
    private TextView tvText;
    private TextView tvLine;
    private ConstraintLayout mainLayout;
    private Button bSignIn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferencesCheck;
    private SharedPreferences.Editor editor;
    private IdentificationFragment identificationFragment;
    private RegistrationFragment registrationFragment;

    public static boolean isEntered;
    public static boolean isRegFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferencesCheck = getSharedPreferences(PREF_CHECKER, MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(PREF_REG, MODE_PRIVATE);
        isEntered = sharedPreferencesCheck.getBoolean(KEY_ENTRY, false);
        if (isEntered){
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        }

        initElements();
        initListeners();

        registrationFragment = new RegistrationFragment();
        identificationFragment = new IdentificationFragment();

        setFragmentOnFrame();

    }

    private void initListeners() {
        tvSignUp.setOnClickListener(v -> {
            if (isRegFragment) {
                changeActivityForIdentifyFragment();

            } else {
                changeActivityForRegistryFragment();
            }
        });

        bSignIn.setOnClickListener(v -> {
           if(!isRegFragment){
               identify();
           }else {
               registration();
           }
        });
    }

    public void changeActivityForIdentifyFragment() {
        isRegFragment = false;
        setFragmentOnFrame();
        bSignIn.setText("SIGN IN");
        bSignIn.setTextColor(Color.parseColor("#FFF44336"));
        bSignIn.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        tvSignUp.setText("Sign Up");
        tvSignUp.setTextColor(Color.parseColor("#FFFFFFFF"));
        tvLine.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        tvText.setText(R.string.not_a_member_yet);
        tvText.setTextColor(Color.parseColor("#FFFFFFFF"));
        mainLayout.setBackgroundColor(Color.parseColor("#FFF44336"));
    }

    private void changeActivityForRegistryFragment(){
        isRegFragment = true;
        setFragmentOnFrame();
        bSignIn.setText("SIGN UP");
        bSignIn.setTextColor(Color.parseColor("#FFFFFFFF"));
        bSignIn.setBackgroundColor(Color.parseColor("#FFF44336"));
        tvSignUp.setText("Sign In");
        tvSignUp.setTextColor(Color.parseColor("#FF000000"));
        tvLine.setBackgroundColor(Color.parseColor("#FF000000"));
        tvText.setText(R.string.already_have_an_account);
        tvText.setTextColor(Color.parseColor("#FF000000"));
        mainLayout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
    }

    private void initElements() {
        tvSignUp = findViewById(R.id.tvSignUp);
        tvText = findViewById(R.id.tvText);
        tvLine = findViewById(R.id.tvLine);
        bSignIn = findViewById(R.id.bSignIn);
        mainLayout = findViewById(R.id.mainLayout);

    }

    private void registration() {
        String userLogin = registrationFragment.getEtNewLogin().getText().toString();
        String password = registrationFragment.getEtNewPass().getText().toString();
        String password2 = registrationFragment.getEtNewPass2().getText().toString();

        if (!userLogin.equals("") && !password.equals("") && password.equals(password2)){
            editor = sharedPreferencesCheck.edit();
            editor.putBoolean(KEY_ENTRY, true);
            editor.apply();

            editor = sharedPreferences.edit();
            editor.putString(KEY_LOGIN, userLogin);
            editor.putString(KEY_PASSWORD, password);
            editor.apply();
            isRegFragment = false;
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }
        else {
            Toast toast = Toast.makeText(this, "wrong input", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, gravityToast);
            toast.show();
        }
    }

    private void identify() {
        String userLogin = identificationFragment.getEtLogin().getText().toString();
        String password = identificationFragment.getEtPassword().getText().toString();
        String userLogin1 = sharedPreferences.getString(KEY_LOGIN, "");
        String password1 = sharedPreferences.getString(KEY_PASSWORD, "");
        if (userLogin.equals(userLogin1) && password.equals(password1)){
            editor = sharedPreferencesCheck.edit();
            editor.putBoolean(KEY_ENTRY, true);
            editor.apply();
            startActivity(new Intent(this, StartActivity.class));
            finish();
        }else {
            Toast toast = Toast.makeText(MainActivity.this, "wrong login or password", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, gravityToast);
            toast.show();
        }

    }

    private  void setFragmentOnFrame(){
        if (!isRegFragment) {
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.frameForFragments, identificationFragment).
                    commit();
        } else {
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.frameForFragments, registrationFragment).
                    commit();
        }


    }
}