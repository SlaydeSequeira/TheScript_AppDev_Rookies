package com.example.thescript.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.thescript.R;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {
    int count;
    String[] data;
    String[] data2;
    Context context;
    String[] cost;

    public RecyclerAdapter2(Context context, String[] data, String[] data2, int count, String[] cost)
    {
        this.data=data;
        this.data2=data2;
        this.context=context;
        this.count = count;
        this.cost = cost;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.color,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setBackgroundColor(Color.parseColor(data2[position]));
        holder.textView.setText(data[position]);
        holder.textView2.setText("Quantity: "+ cost[position]);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        TextView textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView= itemView.findViewById(R.id.image);
            textView2 = itemView.findViewById(R.id.text1);
        }
    }
}
