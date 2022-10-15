package com.flats.talkingflatsapp.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
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

    Context context;
    List<Order> orders;

    public OrderAdapter(Context context, List<Order> books) {
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bookItems = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        return new BookAdapter.BookViewHolder(bookItems);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        //int imageId = context.getResources().getIdentifier("ic_" + books.get(position).getImg(), "drawable", context.getPackageName());
        //holder.bookImage.setImageResource(imageId);

        holder.bookTitle.setText(books.get(position).getTitle());
        holder.bookType.setText(books.get(position).getType());
        holder.pageNumber.setText(books.get(position).getNumber_of_pages());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookActivity.class);

                //intent.putExtra("bookImage",  books.get(position).getTitle());
                intent.putExtra("bookTitle", books.get(position).getTitle());
                intent.putExtra("bookType", books.get(position).getType());
                intent.putExtra("bookAuthor", books.get(position).getAuthor());
                intent.putExtra("from", "reading_library");
                //intent.putExtra("bookDescription", books.get(position).getDescription().toString());
                //intent.putExtra("bookText", books.get(position).getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static final class OrderViewHolder extends RecyclerView.ViewHolder {


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            typeOfCar = itemView.findViewById(R.id.typeOfCar);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookType = itemView.findViewById(R.id.bookType);
            pageNumber = itemView.findViewById(R.id.pageNumber);
        }
    }
}