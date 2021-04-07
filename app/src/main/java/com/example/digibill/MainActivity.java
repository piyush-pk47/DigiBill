package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase database;
    // this data base stores stock

    public void addItem(View view)
    {
        Intent intent=new Intent(getApplicationContext(),EditItem.class);
        startActivity(intent);
        //This opens activity where you can add item
    }
    public void showStock(View view)
    {
        Intent intent=new Intent(getApplicationContext(),ShowStock.class);
        startActivity(intent);
        //This opens activity where you can see stock of the store
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}