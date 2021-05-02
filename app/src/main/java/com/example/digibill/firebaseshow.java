package com.example.digibill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.digibill.EditItem.user;

public class firebaseshow extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase db;//=FirebaseDatabase.getInstance();
    private DatabaseReference root;//=db.getReference().child("main").child("piyush");
    public static MyAdapter adapter;
    private ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebaseshow);
        recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db=FirebaseDatabase.getInstance();
        root=db.getReference().child("main").child(MainActivity.key);

        list=new ArrayList<>();

        adapter=new MyAdapter(this,list);
        recyclerView.setAdapter(adapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Model model=dataSnapshot.getValue(Model.class);
                    Log.i("hello",model.getName());
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}