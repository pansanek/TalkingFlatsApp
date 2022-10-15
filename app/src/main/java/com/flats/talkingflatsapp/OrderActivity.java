package com.flats.talkingflatsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.flats.talkingflatsapp.adapter.OrderAdapter;
import com.flats.talkingflatsapp.order.Order;

import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button endDayButton, exitButton;
    OrderAdapter orderAdapter;
    List<Order> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        recyclerView = findViewById(R.id.recyclerView);
        exitButton = findViewById(R.id.exitButton);

        generateOrders();
        orderAdapter = new OrderAdapter(this, orders);

    }

    public void generateOrders() {
        for(int i = 0; i < 10; i++){
        orders.add(new Order(String.valueOf(((i + 1) * 2)) + ":15", "Трактор", "Перенести 20 тонн говна", "2 часа"));
        }
    }


}
