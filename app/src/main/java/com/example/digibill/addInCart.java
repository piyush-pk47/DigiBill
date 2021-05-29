package com.example.digibill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addInCart extends AppCompatActivity {

    RecyclerView recview;
    fireBaseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_in_cart);
        Button print=findViewById(R.id.printitem);
        recview=findViewById(R.id.cartRec);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference root=db.getReference().child("main").child(MainActivity.key);
        FirebaseRecyclerOptions<Model> options=
                new FirebaseRecyclerOptions.Builder<Model>()
                .setQuery(root,Model.class)
                .build();

        adapter=new fireBaseAdapter(options,this);
        recview.setAdapter(adapter);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(addInCart.this,inovice_activity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                find(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                find(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void find(String s)
    {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference root=db.getReference().child("main").child(MainActivity.key);
        FirebaseRecyclerOptions<Model> options=
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(root.orderByChild("name").startAt(s).endAt(s+"\uf8ff"),Model.class)
                        .build();

        adapter=new fireBaseAdapter(options,this);
        adapter.startListening();
        recview.setAdapter(adapter);

    }
}