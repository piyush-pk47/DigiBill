package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static com.example.digibill.MainActivity.cartList;

public class inovice_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int n=cartList.size();
        String nstr=String.valueOf(n);
        for(int i=0;i<cartList.size();++i)
        {
            Log.i("item",cartList.get(i).getName());
        }

    }

}