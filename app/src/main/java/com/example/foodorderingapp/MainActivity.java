package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderingapp.Adarpters.MainAdapter;
import com.example.foodorderingapp.Models.MainModel;
import com.example.foodorderingapp.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // gradle(app) e change koresi ei kaj korar jonno, barbar findViewById ar likhte hobe na

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Pizza", "500", "A delicious Italian Chicken Pizza with extra cheese."));
        list.add(new MainModel(R.drawable.chickenbiryani, "Chicken Biryani", "300", "A delicious Hajir Chicken Biryani with extra cheese."));
        list.add(new MainModel(R.drawable.eggbiryani, "Egg Biryani", "200", "A delicious Dhakaia Egg Biryani with extra cheese."));
        list.add(new MainModel(R.drawable.burger, "Burger", "150", "A delicious Burger with extra cheese."));
        list.add(new MainModel(R.drawable.eggbiryani, "Sandwich", "120", "A delicious Chinese  with extra cheese."));
        list.add(new MainModel(R.drawable.friedchicken, "Fried Chicken", "100", "A delicious Fried Chicken."));
        list.add(new MainModel(R.drawable.burger, "Hot Dog", "150", "A delicious Chicken Hot Dog with extra cheese."));

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        ViewCompat.setNestedScrollingEnabled(binding.recyclerview, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}