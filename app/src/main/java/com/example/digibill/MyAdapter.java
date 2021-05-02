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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{

    ArrayList<Model> mList;
    Context context;
    public MyAdapter(Context context,ArrayList<Model> mList)
    {
        this.mList=mList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.prodcutdesign,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        Model model=mList.get(position);
        holder.name.setText(model.getName());
        holder.unit.setText(model.getUnit());
        holder.quantity.setText(model.getQuantity());
        holder.price.setText(model.getPrice());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                                        Intent intent1=new Intent(context.getApplicationContext(),firebaseshow.class);
                                        context.startActivity(intent1);
                                        ((Activity)context).finish();
                                        dialog.dismiss();
                                    }
                                });
                    }
                });

                dialog.show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference().child("main").child(MainActivity.key)
                        .child(model.getName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent1=new Intent(context.getApplicationContext(),firebaseshow.class);
                        context.startActivity(intent1);
                        ((Activity)context).finish();
                    }
                });
            }
        });




    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,unit,price,quantity;
        ImageView edit,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nametxt);
            unit=itemView.findViewById(R.id.unittxt);
            price=itemView.findViewById(R.id.pricetxt);
            quantity=itemView.findViewById(R.id.quantitytxt);
            edit=itemView.findViewById(R.id.editbutton);
            delete=itemView.findViewById(R.id.deletebutton);
        }
    }
}
