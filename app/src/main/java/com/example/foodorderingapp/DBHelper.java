package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderingapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBname = "mydatabase.db";
    final static int DBversion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, DBversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "qunatity int," +
                        "description text," +
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertOrder(String name, String phone, int price, int image, int quantity, String desc, String foodname) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name); // 1
        values.put("phone", phone); // 2
        values.put("price", price); // 3
        values.put("image", image); // 4
        values.put("quantity", quantity); // 5
        values.put("description", desc); // 6
        values.put("foodname", foodname); // 7

        long id = database.insert("orders", null, values);

        return id <= 0 ? false : true;
    }

    public ArrayList<OrdersModel> getOrder() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select id, foodname, image, price from orders", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) +"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) +"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = "+ id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updateOrder(int id, String name, String phone, int price, int image, int quantity, String desc, String foodname) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name); // 1
        values.put("phone", phone); // 2
        values.put("price", price); // 3
        values.put("image", image); // 4
        values.put("quantity", quantity); // 5
        values.put("description", desc); // 6
        values.put("foodname", foodname); // 7

        long row = database.update("orders", values, "id="+id, null);

        return row <= 0 ? false : true;
    }

    public int deleteOrder(String id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id="+id, null);
    }
}
