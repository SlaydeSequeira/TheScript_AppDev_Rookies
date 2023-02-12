package com.example.thescript.fragment;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thescript.HomePage2;
import com.example.thescript.R;
import com.example.thescript.adapter.RecyclerAdapter2;
import com.example.thescript.adapter.RecyclerAdapter3;

public class Event extends Fragment {
    RecyclerView recyclerView;
    RecyclerAdapter3 adapter;
    String Image[]=      {  "https://www.bookeventz.com/blog/wp-content/uploads/2021/02/xFeeding-India-Donate-Food.png.pagespeed.ic.GEQEKziwz3.jpg",
        "https://d1l18ops95qbzp.cloudfront.net/wp-content/2020/05/16154311/Central-Union-Church-Food-Drive-2-scaled.jpg","https://cdn.thewire.in/wp-content/uploads/2020/03/28173950/2020_3img27_Mar_2020_PTI27-03-2020_000199B-copy-1200x600.jpg"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_event, container, false);

        recyclerView=view.findViewById(R.id.reeeeeeee);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerAdapter3(getActivity(), Image);
        recyclerView.setAdapter(adapter);
        return view;
    }
}