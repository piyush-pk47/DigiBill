package com.example.digibill;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{

    private List<modelClass> productList;

    public Adapter(List<modelClass> productList)
    {
        this.productList=productList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.prodcutdesign,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position)
    {
        String name=productList.get(position).getTextview();
        String quantity=productList.get(position).getTextview1();
        String price=productList.get(position).getTextview2();

        holder.setData(name,quantity,price);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView textView1;
        private TextView textView2;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textview);
            textView1=itemView.findViewById(R.id.nametxt);
            textView2=itemView.findViewById(R.id.textview2);
        }

        public void setData(String name, String quantity, String price)
        {
            textView.setText(name);
            textView1.setText(quantity);
            textView2.setText(price);
        }
    }
}
