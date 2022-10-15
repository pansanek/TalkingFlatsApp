package com.flats.talkingflatsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Login extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Nickname";
    public static final String APP_PREFERENCES_PASSWORD = "Password";
    TextInputEditText editNickName;
    TextInputEditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editNickName = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        editor = mSettings.edit();
    }

    public void OnClickLogin(View view) {


        editor.putString(APP_PREFERENCES_NAME, editNickName.getText().toString());
        editor.putString(APP_PREFERENCES_PASSWORD, editPassword.getText().toString());
        editor.apply();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings != null && !mSettings.getString(APP_PREFERENCES_NAME,"").equals("")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(getApplicationContext(), "Авторизация не прошла",
                    Toast.LENGTH_SHORT).show();
        }
    }
}