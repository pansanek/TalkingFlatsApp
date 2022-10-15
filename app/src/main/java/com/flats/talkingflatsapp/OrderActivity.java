package com.flats.talkingflatsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.flats.talkingflatsapp.adapter.OrderAdapter;
import com.flats.talkingflatsapp.order.Order;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button endDayButton, exitButton;
    OrderAdapter orderAdapter;
    List<Order> orders;
    private static final String TAG = "Hi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        exitButton = findViewById(R.id.exitButton);
        orders = new ArrayList<>();
        orderAdapter = new OrderAdapter(this, orders);
        showList();


        recyclerView.setAdapter(orderAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(OrderActivity.this, UserLocationActivity.class);
                        Order ordAdd = orders.get(position);
                        intent.putExtra("hello", ordAdd.getAddress());
                        startActivity(intent);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    public void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://192.168.0.190/mybase/connect.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray array = obj.getJSONArray("result");
                            for (int i = 0; i<array.length();i++){
                                JSONObject provObj = array.getJSONObject(i);
                                Order ord = new Order(provObj.getString("startTime"),provObj.getString("car_id"),provObj.getString("Order_time"),provObj.getString("Coordinates"));
                                orders.add(ord);
                            }
                            orderAdapter.notifyDataSetChanged();
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
    /*public void generateOrders() {
        orders.add(new Order("15:15", "Трактор", "Перенести 20 тонн ", "2 часа"));
        orders.add(new Order("13:15", "Самосвал", "Перенести 10 тонн ", "3 часа"));
        orders.add(new Order("11:15", "Кран", "Поднять перевернувшийся трактор", "1 час"));
        orders.add(new Order("12:15", "Эскаватор", "Выкопать яму", "6 часов"));
        orders.add(new Order("18:15", "Грузовик", "Перенести 110 тонн ", "1 часа"));
        orders.add(new Order("19:15", "Автомобиль", "Перевести Петренко А.А. ", "39 минут"));
        orderAdapter.notifyDataSetChanged();
    }*/
    public void OnClickEndDay(View view) {
        Toast.makeText(getApplicationContext(), "Окончание рабочего дня", Toast.LENGTH_LONG).show();
        SharedPreferences preferences = getSharedPreferences("PrefStartDay", 0);
        preferences.edit().clear().commit();
        this.finish();
    }
    public void OnClickDelogin(View view) {
        SharedPreferences preferences = getSharedPreferences("mysettings", 0);
        preferences.edit().clear().commit();
        this.finish();
    }
}
