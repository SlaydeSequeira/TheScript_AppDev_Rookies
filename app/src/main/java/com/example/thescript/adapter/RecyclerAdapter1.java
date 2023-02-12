package com.example.thescript.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thescript.R;

public class RecyclerAdapter1 extends RecyclerView.Adapter<RecyclerAdapter1.ViewHolder> {
    private final Context context;
    private final String[] Feed;
    private final String[] User;
    private final int count;

    @NonNull
    @Override
    public RecyclerAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.feedback,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter1.ViewHolder holder, int position) {
        holder.textView1.setText(Feed[position]);
        holder.textView2.setText("by "+User[position]);
    }
    public RecyclerAdapter1(Context context,String Feed[],String User[],int count)
    {
        this.context=context;
        this.Feed=Feed;
        this.User=User;
        this.count=count;
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1;
        TextView textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1= itemView.findViewById(R.id.text1);
            textView2= itemView.findViewById(R.id.text2);
        }
    }
}
