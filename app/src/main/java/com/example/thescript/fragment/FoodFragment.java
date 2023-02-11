package com.example.thescript.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.thescript.AddFood;
import com.example.thescript.R;
import com.example.thescript.adapter.FoodAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FoodFragment extends Fragment {

    RecyclerView recyclerView;
    FoodAdapter adapter;
    String temp;
    String Title[]=new String[100];
    String Image[]= new String[100];
    String Cost[]= new String[100];
    String Veg[]=new String[100];
    int count;
    String c,a;
    int b;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_food, container, false);
        recyclerView = view.findViewById(R.id.recyclerViews);
        btn = view.findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser;
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String userid = firebaseUser.getUid();
                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("MyUsers").child(userid).child("role");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        a=String.valueOf(snapshot.getValue());
                        b=Integer.parseInt(a);
                        if(b==1) {
                            openAct();
                        }
                        else{
                            Toast.makeText(getActivity(), "This feature is for providers only", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("market");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                c= String.valueOf(snapshot.child("count").getValue());
                count=Integer.parseInt(c);
                for (int i = 0; i < count; i++) {
                    temp = String.valueOf(i);
                    Title[i] = String.valueOf(snapshot.child("Title").child(temp).getValue());
                }
                for (int i=0;i< count;i++){
                    temp = String.valueOf(i);
                    Cost[i]= String.valueOf(snapshot.child("cost").child(temp).getValue());
                }
                for (int i=0;i< count;i++)
                {
                    temp = String.valueOf(i);
                    Image[i]= String.valueOf(snapshot.child("image").child(temp).getValue());
                }
                for (int i=0;i< count;i++)
                {
                    temp = String.valueOf(i);
                    Veg[i]= String.valueOf(snapshot.child("veg").child(temp).getValue());
                }
                adapter = new FoodAdapter(getActivity(),Title,Cost,count,Image,Veg);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return  view;
    }
    public void openAct()
    {
        Intent i = new Intent(getActivity(), AddFood.class);
        startActivity(i);
    }
}