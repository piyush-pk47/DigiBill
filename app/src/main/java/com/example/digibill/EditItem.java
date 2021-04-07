package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditItem extends AppCompatActivity {

    EditText nameText,unitText,prizeText,quantityText;
    String finName,finUnit,finPrize,finQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        nameText=(EditText) findViewById(R.id.name);
        unitText=(EditText) findViewById(R.id.unit);
        prizeText=(EditText) findViewById(R.id.prize);
        quantityText=(EditText) findViewById(R.id.quantity);
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                   finName=String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        unitText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                  finUnit=String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        prizeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    finPrize=String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        quantityText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                  finQuantity=String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
    public void addCurItem(View view)
    {
            MainActivity.database=this.openOrCreateDatabase("stock",MODE_PRIVATE,null);
            MainActivity.database.execSQL("CREATE TABLE IF NOT EXISTS list (name VARCHAR,unit VARCHAR,prize VARCHAR,quantity VARCHAR)");
            MainActivity.database.execSQL("INSERT INTO list (name,unit,prize,quantity) VALUES ('"+finName+"','"+finUnit+"','"+finPrize+"','"+finQuantity+"')");
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

    }
}