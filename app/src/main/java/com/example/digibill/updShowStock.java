package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class updShowStock extends AppCompatActivity {

    RecyclerView stocklist;
    LinearLayoutManager layoutManager;
    List<modelClass> itemlist;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_show_stock);
        inttdata();
        initRecyclerView();

    }

    private void initRecyclerView()
    {
        stocklist=findViewById(R.id.recycle);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        stocklist.setLayoutManager(layoutManager);
        adapter=new Adapter(itemlist);
        stocklist.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void inttdata()
    {
        itemlist = new ArrayList<>();
        MainActivity.database=this.openOrCreateDatabase("stock",MODE_PRIVATE,null);
        Cursor c=MainActivity.database.rawQuery("SELECT * FROM list",null);
        int nameInd=c.getColumnIndex("name");
        int unitInd=c.getColumnIndex("unit");
        int prizeInd=c.getColumnIndex("prize");
        int quantityInd=c.getColumnIndex("quantity");
        int cnt=c.getCount();
        c.moveToFirst();

        for(int i=0;i<cnt;++i)
        {
            String curName=c.getString(nameInd);
            String curUnit=c.getString(unitInd);
            String curPrize=c.getString(prizeInd);
            String curQuantity=c.getString(quantityInd);
            itemlist.add(new modelClass(curName,"Quantity : "+curQuantity+" kg","Price : "+curPrize+" Rs per "+curUnit));
            if(i!=cnt-1)
                c.moveToNext();
        }

    }
}