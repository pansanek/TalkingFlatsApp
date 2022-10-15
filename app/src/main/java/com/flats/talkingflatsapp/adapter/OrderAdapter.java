package com.flats.talkingflatsapp.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flats.talkingflatsapp.MainActivity;
import com.flats.talkingflatsapp.R;
import com.flats.talkingflatsapp.UserLocationActivity;
import com.flats.talkingflatsapp.order.Order;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;
    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orderList = orders;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_adapter_view, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        holder.timeView.setText(this.orderList.get(position).getTimeView());
        holder.typeCar.setText(this.orderList.get(position).getTypeCar());
        holder.typeOfWorkView.setText(this.orderList.get(position).getTypeOfWorkView());
        holder.timeOfWork.setText(this.orderList.get(position).getTimeOfWork());
    }


    @Override
    public int getItemCount() {
        return  this.orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView timeView, typeCar, typeOfWorkView, timeOfWork;
        public OrderViewHolder(View view) {
            super(view);
            timeView = view.findViewById(R.id.timeView);
            typeCar = view.findViewById(R.id.typeOfCar);
            typeOfWorkView = view.findViewById(R.id.typeOfWorkView);
            timeOfWork = view.findViewById(R.id.timeOfWork);

        }
    }

}