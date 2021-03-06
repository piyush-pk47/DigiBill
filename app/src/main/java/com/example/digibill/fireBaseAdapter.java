package com.example.digibill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import static com.example.digibill.MainActivity.cartList;

public class fireBaseAdapter extends FirebaseRecyclerAdapter<Model,fireBaseAdapter.myviewholder>
{
    Context context;

    public fireBaseAdapter(@NonNull FirebaseRecyclerOptions<Model> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Model model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.unit.setText(model.getUnit());
        holder.quantity.setText(model.getQuantity());
        holder.bordertxt.setText("___________________________________________________________________");
        holder.deltebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog=DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.add_item_in_cart))
                        .setExpanded(false)
                        .create();
                View cartView=dialog.getHolderView();
                EditText addUnit=cartView.findViewById(R.id.addUnit);
                Button addIntoCart=cartView.findViewById(R.id.addItemCart);
                addIntoCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                          String s=addUnit.getText().toString();
                          int i=Integer.parseInt(s);
                          String lestr=model.getQuantity();
                          int leftUnit=Integer.parseInt(lestr)-i;

                        //Toast.makeText(context,intunit,Toast.LENGTH_SHORT).show();
                        if(leftUnit==0)
                        {
                            Model add = new Model();
                            add.setName(model.getName());
                            add.setPrice(model.getPrice());
                            add.setQuantity(addUnit.getText().toString());
                            add.setUnit(model.getUnit());
                            cartList.add(add);
                            //Toast.makeText(context,"remove item",Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference().child("main").child(MainActivity.key)
                                    .child(model.getName()).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            dialog.dismiss();
                                        }
                                    });
                        }
                        else if(leftUnit>0)
                        {
                            Model add = new Model();
                            add.setName(model.getName());
                            add.setPrice(model.getPrice());
                            add.setQuantity(addUnit.getText().toString());
                            add.setUnit(model.getUnit());
                            cartList.add(add);
                            Map<String,Object> map=new HashMap<>();
                            map.put("name",model.getName());
                            map.put("price",model.getPrice());
                            map.put("unit",model.getUnit());
                            map.put("quantity",String.valueOf(leftUnit));
                            FirebaseDatabase.getInstance().getReference().child("main").child(MainActivity.key)
                                    .child(model.getName()).updateChildren(map)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            dialog.dismiss();
                                        }
                                    });
                        }
                        else
                        {
                            Toast.makeText(context,"can't be added",Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });
        holder.updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.updateitem))
                        .setExpanded(false)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                View currview=dialog.getHolderView();
                EditText chaName=currview.findViewById(R.id.chagename);
                EditText chaUnit=currview.findViewById(R.id.unitname);
                EditText chaPrice=currview.findViewById(R.id.pricename);
                EditText chaQuant=currview.findViewById(R.id.quantityname);
                Button updatebttn=currview.findViewById(R.id.updateit);
                chaName.setText(model.getName());
                chaPrice.setText(model.getPrice());
                chaUnit.setText(model.getUnit());
                chaQuant.setText(model.getQuantity());
                updatebttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("name",chaName.getText().toString());
                        map.put("price",chaPrice.getText().toString());
                        map.put("unit",chaUnit.getText().toString());
                        map.put("quantity",chaQuant.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("main").child(MainActivity.key)
                                .child(model.getName()).updateChildren(map)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

                dialog.show();


            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,unit,price,quantity,bordertxt;
        ImageButton deltebtn,updatebtn;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.cartName);
            unit=(TextView)itemView.findViewById(R.id.cartUnit);
            price=(TextView)itemView.findViewById(R.id.cartPrice);
            quantity=(TextView)itemView.findViewById(R.id.cartquantity);
            deltebtn=(ImageButton)itemView.findViewById(R.id.delebtn);
            bordertxt=(TextView)itemView.findViewById(R.id.border);
            updatebtn= (ImageButton) itemView.findViewById(R.id.updateId);
        }
    }
}
