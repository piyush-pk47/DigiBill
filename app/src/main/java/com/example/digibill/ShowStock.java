package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ShowStock extends AppCompatActivity {
    ListView listView;
    // this will show all items
    ArrayList<String> arrayList=new ArrayList<>();
    TextView dateView;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stock);
        listView=findViewById(R.id.stockList);
        dateView=findViewById(R.id.textView);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        String currentDateandTime = sd.format(new Date());
        dateView.setText("Stock until date : "+currentDateandTime);
        MainActivity.database=this.openOrCreateDatabase("stock",MODE_PRIVATE,null);
        Cursor c=MainActivity.database.rawQuery("SELECT * FROM list",null);
        int nameInd=c.getColumnIndex("name");
        int cnt=c.getCount();

        c.moveToFirst();

        for(int i=0;i<cnt;++i)
        {
            arrayList.add(c.getString(nameInd));
            if(i!=cnt-1)
                c.moveToNext();
        }
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),showItem.class);
                intent.putExtra("stockName",arrayList.get(position));
                startActivity(intent);
            }
        });
    }
}