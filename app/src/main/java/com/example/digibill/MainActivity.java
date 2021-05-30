package com.example.digibill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.digibill.EditItem.user;

public class MainActivity extends AppCompatActivity {

    //this is sql version

    public static SQLiteDatabase database;
    public static List<modelClass> curlist;
    public static Intent intent;
    public static String key;
    public static List<Model> cartList;
    // this data base stores stock

    public void addItem(View view)
    {
        Intent intent=new Intent(getApplicationContext(),EditItem.class);
        startActivity(intent);
        //this.finish();
        //This opens activity where you can add item
    }

    public void showStock(View view)
    {
        Intent intent=new Intent(getApplicationContext(),addInCart.class);
        startActivity(intent);
        //this.finish();
        //This opens activity where you can see stock of the store
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseUser current= FirebaseAuth.getInstance().getCurrentUser();
        key=current.getUid();
        //Toast.makeText(this, "" + current.getUid(), Toast.LENGTH_SHORT).show();
        cartList=new ArrayList<>();

    }
}