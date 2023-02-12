package com.example.thescript.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.thescript.R;

public class RecyclerAdapter3 extends RecyclerView.Adapter<RecyclerAdapter3.ViewHolder>{

    private final Context context;
    private final String[] Image;
    @NonNull
    @Override
    public RecyclerAdapter3.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.add,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    public RecyclerAdapter3(Context context,String Image[])
    {
        this.context=context;
        this.Image=Image;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter3.ViewHolder holder, int position) {
        Glide.with(context)
                .load(Image[position])
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
