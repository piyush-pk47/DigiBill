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
import android.widget.Filter;
import android.widget.Filterable;
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
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.AppCompatActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable
{

    ArrayList<Model> mList;
    Context context;
    ArrayList<Model> mlistfull;
    public MyAdapter(Context context,ArrayList<Model> mList)
    {
        this.mList=mList;
        this.context=context;
        mlistfull=new ArrayList<>(mList);
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

    @Override
    public Filter getFilter() {
        return exmplefilter;
    }
    private Filter exmplefilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Model> filterdlist=new ArrayList<>();
            if(constraint==null || constraint.length()==0)
            {
                filterdlist.addAll(mlistfull);
            }
            else
            {
                String pattern=constraint.toString().toLowerCase().trim();
                for(Model item:mlistfull)
                {
                    if(item.getName().toLowerCase().contains(pattern))
                    {
                        filterdlist.add(item);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filterdlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
              mList.clear();
              mList.addAll((List)results.values);
              notifyDataSetChanged();
        }
    };

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
//public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder>
//{
//    private List<Model> currentList;
//    public cartAdapter(List<Model> currentList)
//    {
//        this.currentList=currentList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.invoice_item,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//          String srNum=String.valueOf(position);
//          String name=currentList.get(position).getName();
//          String unit=currentList.get(position).getUnit();
//          String price=currentList.get(position).getPrice();
//          int totalInt=Integer.parseInt(unit)*Integer.parseInt(price);
//          String total=String.valueOf(totalInt);
//
//          holder.setData(srNum,name,unit,price,total);
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return currentList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder
//    {
//
//        private TextView srNo,name,unit,priceUnit,total;
//        public ViewHolder(@NonNull View itemView)
//        {
//            super(itemView);
//            srNo=itemView.findViewById(R.id.srNum);
//            name=itemView.findViewById(R.id.nameId);
//            unit=itemView.findViewById(R.id.unitTaken);
//            priceUnit=itemView.findViewById(R.id.pricePerUnit);
//            total=itemView.findViewById(R.id.totalPrice);
//        }
//        public void setData(String srNum,String nameId,String unitTaken,String pricePerUnit,String totalPrice)
//        {
//            srNo.setText(srNum);
//            name.setText(nameId);
//            unit.setText(unitTaken);
//            priceUnit.setText(pricePerUnit);
//            total.setText(totalPrice);
//        }
//
//    }
//}