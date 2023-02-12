package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.thescript.adapter.RecyclerAdapter1;
import com.example.thescript.adapter.RecyclerAdapter2;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Notice extends AppCompatActivity {
RecyclerAdapter1 adapter;
RecyclerView recyclerView;
String count,temp;
String Feed[]= new String[100];
String User[]= new String[100];
int c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference("Notice");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count=String.valueOf(snapshot.child("nc").getValue());
                c=Integer.parseInt(count);
                for (int i = 0; i < c  ; i++) {
                    temp = String.valueOf(i);
                    Feed[i] = String.valueOf(snapshot.child(temp).getValue());
                    User[i] = String.valueOf(snapshot.child("user").child(temp).getValue());
                    newView();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void newView() {
        recyclerView=findViewById(R.id.test);
        recyclerView.setLayoutManager(new LinearLayoutManager(Notice.this));
        adapter = new RecyclerAdapter1(Notice.this,Feed,User,c);
        recyclerView.setAdapter(adapter);
    }

}