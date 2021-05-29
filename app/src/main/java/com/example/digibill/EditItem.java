package com.example.digibill;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

import static com.example.digibill.MainActivity.intent;


public class EditItem extends AppCompatActivity {

    EditText nameText,unitText,prizeText,quantityText;
    String finName,finUnit,finPrize,finQuantity;
    public static String user;//="piyush";
    private FirebaseDatabase db;//=FirebaseDatabase.getInstance();
    private DatabaseReference root;//=db.getReference().child("main").child(user);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        nameText=(EditText) findViewById(R.id.name);
        unitText=(EditText) findViewById(R.id.unit);
        prizeText=(EditText) findViewById(R.id.prize);
        quantityText=(EditText) findViewById(R.id.quantity);
        user=MainActivity.key;
        db=FirebaseDatabase.getInstance();
        root=db.getReference().child("main").child(user);

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
            HashMap<String,String> usermap=new HashMap<>();
            usermap.put("name",finName);
            usermap.put("unit",finUnit);
            usermap.put("price",finPrize);
            usermap.put("quantity",finQuantity);
            root.child(finName).setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"data saved",Toast.LENGTH_SHORT).show();
                }
            });
            
            this.finish();

    }
}
