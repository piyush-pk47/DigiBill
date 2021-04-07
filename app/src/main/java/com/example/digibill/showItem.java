package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class showItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_item);
        TextView showItem=findViewById(R.id.textView3);
        Intent intent=getIntent();
        String ItemName=intent.getStringExtra("stockName");
        Log.i("name:",ItemName);
        MainActivity.database=this.openOrCreateDatabase("stock",MODE_PRIVATE,null);
        Cursor c=MainActivity.database.rawQuery("SELECT * FROM list WHERE name='"+ItemName+"' ",null);
        //name VARCHAR,unit VARCHAR,prize VARCHAR,quantity VARCHAR
        int nameInd=c.getColumnIndex("name");
        int unitInd=c.getColumnIndex("unit");
        int prizeInd=c.getColumnIndex("prize");
        int quantityInd=c.getColumnIndex("quantity");
        c.moveToFirst();
        String curName=c.getString(nameInd);
        String curUnit=c.getString(unitInd);
        String curPrize=c.getString(prizeInd);
        String curQuantity=c.getString(quantityInd);
        String mess="Name : "+curName+"\n\n\n"+"Quantity : "+curQuantity+" "+curUnit+"\n\n\n"+"Prize : "+curPrize+" Rs per " + curUnit;
        showItem.setText(mess);

    }
}