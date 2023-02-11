package com.example.thescript;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class buy extends AppCompatActivity {
String d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        d=getIntent().getStringExtra("d");
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getReference("hello");
    }
}