package com.flats.talkingflatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    SharedPreferences PrefStartDay;
    SharedPreferences.Editor editor;
    public static final String APP_PREFERENCES = "PrefStartDay";
    public static final String APP_PREFERENCES_DAY = "START";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        PrefStartDay = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        editor = PrefStartDay.edit();

    }

    public void OnClickStartDay(View view) {
        progressBar.setVisibility(View.VISIBLE);
        editor.putString(APP_PREFERENCES_DAY, "START");
        editor.apply();
        Thread thread = new Thread(){
            @Override
            public void run() {

                super.run();
                try {
                    sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    PermissionListener permissionlistener = new PermissionListener() {
                        @Override
                        public void onPermissionGranted() {
                            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                            startActivity(intent);
                            //progressBar.setVisibility(View.INVISIBLE);
                            finish();
                        }

                        @Override
                        public void onPermissionDenied(List<String> deniedPermissions) {
                            Toast.makeText(MainActivity.this, "Доступ запрещен\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                        }


                    };

                    TedPermission.with(MainActivity.this)
                            .setPermissionListener(permissionlistener)
                            .setDeniedMessage("Для использования приложения необходимо предоставить доступ к местоположению")
                            .setPermissions( Manifest.permission.ACCESS_FINE_LOCATION)
                            .check();
                }
            }
        };

        thread.start();

    }


    @Override
    public void onResume() {
        super.onResume();
        PrefStartDay = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        PrefStartDay = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (PrefStartDay != null && !PrefStartDay.getString(APP_PREFERENCES_DAY,"").equals("")) {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
            finish();
        } else {
            // If sign in fails, display a message to the user.
            Toast.makeText(getApplicationContext(), "Рабочий день не начат",
                    Toast.LENGTH_SHORT).show();
        }
    }


}