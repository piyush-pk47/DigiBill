package com.example.digibill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder>
{
    List<Model> currentList;
    public cartAdapter(List<Model> currentList)
    {
        this.currentList=currentList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model=currentList.get(position);
        holder.srNo.setText(String.valueOf(position));
        holder.priceUnit.setText(model.getPrice());
        holder.unit.setText(model.getQuantity());
        int totalInt=(Integer.parseInt(model.getQuantity()))*(Integer.parseInt(model.getPrice()));
        holder.total.setText(String.valueOf(totalInt));
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView srNo,name,unit,priceUnit,total;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            srNo=itemView.findViewById(R.id.srNum);
            name=itemView.findViewById(R.id.nameId);
            unit=itemView.findViewById(R.id.unitTaken);
            priceUnit=itemView.findViewById(R.id.pricePerUnit);
            total=itemView.findViewById(R.id.totalPrice);
        }
    }
}

