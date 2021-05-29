package com.example.digibill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.digibill.EditItem.user;

public class firebaseshow extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    private FirebaseDatabase db;//=FirebaseDatabase.getInstance();
//    private DatabaseReference root;//=db.getReference().child("main").child("piyush");
//    public static MyAdapter adapter;
//    private ArrayList<Model> list;
    private MyAdapter adapter;
    private ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebaseshow);
        addinlist();
        setupRecycler();
//        recyclerView=findViewById(R.id.recycle);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        db=FirebaseDatabase.getInstance();
//        root=db.getReference().child("main").child(MainActivity.key);
//
//        list=new ArrayList<>();
//
//        adapter=new MyAdapter(this,list);
//        recyclerView.setAdapter(adapter);
//
//
//        root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot:snapshot.getChildren())
//                {
//                    Model model=dataSnapshot.getValue(Model.class);
//                    Log.i("hello",model.getName());
//                    list.add(model);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

    }

    private void setupRecycler()
    {
        RecyclerView recyclerView=findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        adapter=new MyAdapter(this,list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void addinlist()
    {
        list=new ArrayList<>();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference root=db.getReference().child("main").child(MainActivity.key);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        MenuItem searchitem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchitem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}