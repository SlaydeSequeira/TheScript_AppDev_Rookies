package com.example.thescript.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thescript.R;
import com.example.thescript.dis;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    private final Context context;
    private final String[] title;
    private final int count;
    private final String[] cost;
    private final String[] image;
    private final String[] Veg;
    private String[] image2 = new String[100];
    private String[] image1 = new String[100];
    private String[] cost2 = new String[100];
    private String[] cost1 = new String[100];
    private String[] title2 = new String[100];
    private String[] title1 = new String[100];
    private String[] Veg2 = new String[100];
    private String[] Veg1 = new String[100];

    public FoodAdapter(Context context, String[] title, String[] cost, int count, String[] image, String[] Veg) {
        this.image = image;
        this.cost = cost;
        this.context= context;
        this.Veg=Veg;
        for(int i=0;i<count;i++) {
            if(i%2==0) {
                title1[(i) / 2] = title[i];
                cost1[(i) / 2] = cost[i];
                image1[i/2]=image[i];
                Veg1[i/2]=Veg[i];
            }
            else {
                title2[(i) / 2] = title[i];
                cost2[(i)/2] = cost[i];
                image2[i/2]=image[i];
                Veg2[i/2]=Veg[i];
            }
        }
        this.title = title;
        this.count = count;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.buy_food1,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView1.setText(title1[position]);
        holder.textView2.setText(title2[position]);
        holder.textView5.setText(Veg1[position]);
        holder.textView6.setText(Veg2[position]);
        Glide.with(context)
                .load(image1[position])
                .into(holder.imageView1);
        Glide.with(context)
                .load(image2[position])
                .into(holder.imageView2);
        if(cost2[position]!= null) {
            holder.textView3.setText(cost1[position] + "kgs");
            holder.textView4.setText(cost2[position] + "kgs");
        }
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), dis.class);
                intent.putExtra("a",title1[position]);
                intent.putExtra("b",cost1[position]);
                intent.putExtra("c",image1[position]);
                String pos= String.valueOf(2*position);
                intent.putExtra("d",pos);
                view.getContext().startActivity(intent);
            }
        });
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(),dis.class);
                intent.putExtra("a",title2[position]);
                intent.putExtra("b",cost2[position]);
                intent.putExtra("c",image2[position]);
                String pos= String.valueOf(2*position+1);
                intent.putExtra("d",pos);
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (count+1)/2;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        ImageView imageView1;
        ImageView imageView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.text1);
            textView2=itemView.findViewById(R.id.text2);
            textView3=itemView.findViewById(R.id.cost1);
            textView4=itemView.findViewById(R.id.cost2);
            textView5=itemView.findViewById(R.id.veg1);
            textView6=itemView.findViewById(R.id.veg2);
            imageView1=itemView.findViewById(R.id.image1);
            imageView2=itemView.findViewById(R.id.image2);
        }
    }
}






