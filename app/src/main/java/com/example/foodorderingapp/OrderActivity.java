package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorderingapp.Adarpters.MainAdapter;
import com.example.foodorderingapp.Adarpters.OrdersAdapter;
import com.example.foodorderingapp.Models.MainModel;
import com.example.foodorderingapp.Models.OrdersModel;
import com.example.foodorderingapp.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_orders);
        setContentView(binding.getRoot());

//        ArrayList<OrdersModel> list = new ArrayList<>();

//        list.add(new OrdersModel(R.drawable.burger, "Pizza", "500", "12345"));
//        list.add(new OrdersModel(R.drawable.chickenbiryani, "Chicken Biryani", "300", "12345"));
//        list.add(new OrdersModel(R.drawable.eggbiryani, "Egg Biryani", "200", "12345"));
//        list.add(new OrdersModel(R.drawable.burger, "Burger", "150", "12345"));
//        list.add(new OrdersModel(R.drawable.eggbiryani, "Sandwich", "120", "12345"));
//        list.add(new OrdersModel(R.drawable.chickenbiryani, "Fried Chicken", "100", "12345"));
//        list.add(new OrdersModel(R.drawable.burger, "Hot Dog", "150", "12345"));


        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrder();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        ViewCompat.setNestedScrollingEnabled(binding.orderRecyclerView, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}