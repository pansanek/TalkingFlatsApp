package com.flats.talkingflatsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.flats.talkingflatsapp.order.Order;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Login extends AppCompatActivity {
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String APP_PREFERENCES_NAME = "Nickname";
    public static final String APP_PREFERENCES_PASSWORD = "Password";
    TextInputEditText editNickName;
    TextInputEditText editPassword;
    Boolean log;
    List<Driver> drivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editNickName = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        editor = mSettings.edit();
        drivers = new ArrayList<>();
        getDrivers();


    }


    public void OnClickLogin(View view) {
        for (Driver element : drivers) {
            if (element.getLogin().equals(editNickName.getText().toString()) && element.getPassword().equals(editPassword.getText().toString())) {
                editor.putString(APP_PREFERENCES_NAME, editNickName.getText().toString());
                editor.putString(APP_PREFERENCES_PASSWORD, editPassword.getText().toString());
                editor.apply();
                log = true;
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                break;
            }
        }
        if(!log) Toast.makeText(Login.this, "Такого пользователя нет",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResume() {
        super.onResume();
        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (mSettings != null && !mSettings.getString(APP_PREFERENCES_NAME, "").equals("")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(getApplicationContext(), "Авторизация не прошла",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void getDrivers() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.0.190/mybase/driverlogin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("result");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject provObj = array.getJSONObject(i);
                                Driver driver = new Driver(provObj.getString("login"), provObj.getString("password"));
                                drivers.add(driver);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }


        ) {

        };
        Handler.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }
}