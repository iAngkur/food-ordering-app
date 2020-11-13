package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.foodorderingapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_detail);
        setContentView(binding.getRoot());

        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String name = getIntent().getStringExtra("name");
            final String description = getIntent().getStringExtra("desc");

            binding.detailImage.setImageResource(image);
            binding.priceLevel.setText(String.format("%d", price));
            binding.foodName.setText(name);
            binding.detailDescription.setText(description);

            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            description,
                            name
                    );

                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Ordered Successfull", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(DetailActivity.this, "Ordered Failed", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            final int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);

            final int image = cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.priceLevel.setText(String.format("%d", cursor.getInt(3)));
            binding.foodName.setText(cursor.getString(7));
            binding.detailDescription.setText(cursor.getString(6));

            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));

            binding.insertBtn.setText("Update Now");
            binding.insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   boolean isUpdated = helper.updateOrder(
                            id,
                            binding.nameBox.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            Integer.parseInt(binding.priceLevel.getText().toString()),
                            image,
                            1,
                            binding.detailDescription.getText().toString(),
                            binding.foodName.getText().toString()
                    );
                    if (isUpdated)
                        Toast.makeText(DetailActivity.this, "Ordered Updated Successfull", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(DetailActivity.this, "Update Failed", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}