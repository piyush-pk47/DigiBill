package com.example.digibill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.digibill.EditItem.user;

public class updShowStock extends AppCompatActivity {

   private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upd_show_stock);



    }

//    private void initRecyclerView()
//    {
//        stocklist=findViewById(R.id.recycle);
//        layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        stocklist.setLayoutManager(layoutManager);
//        adapter=new Adapter(itemlist);
//        stocklist.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }
//
//    private void inttdata()
//    {
//        itemlist = new ArrayList<>();
////        MainActivity.database=this.openOrCreateDatabase("stock",MODE_PRIVATE,null);
////        Cursor c=MainActivity.database.rawQuery("SELECT * FROM list",null);
////        int nameInd=c.getColumnIndex("name");
////        int unitInd=c.getColumnIndex("unit");
////        int prizeInd=c.getColumnIndex("prize");
////        int quantityInd=c.getColumnIndex("quantity");
////        int cnt=c.getCount();
////        c.moveToFirst();
////        for(int i=0;i<cnt;++i)
////        {
////            String curName=c.getString(nameInd);
////            String curUnit=c.getString(unitInd);
////            String curPrize=c.getString(prizeInd);
////            String curQuantity=c.getString(quantityInd);
////            itemlist.add(new modelClass(curName,"Quantity : "+curQuantity+" kg","Price : "+curPrize+" Rs per "+curUnit));
////            if(i!=cnt-1)
////                c.moveToNext();
////        }
//        int a=0;
//        Log.i("hello",Integer.toString(a));
//        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("main").child(user);
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                {
//                    modelClass model=dataSnapshot.getValue(modelClass.class);
//                    itemlist.add(model);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//
//
//    }
}