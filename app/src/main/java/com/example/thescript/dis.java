package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class dis extends AppCompatActivity {

    String a,b,c,d;
    String e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis);
       Objects.requireNonNull(getSupportActionBar()).hide();
        a=getIntent().getStringExtra("a");
        b=getIntent().getStringExtra("b");
        c=getIntent().getStringExtra("c");
        d=getIntent().getStringExtra("d");
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("market").child("time").child(d);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                e=String.valueOf(snapshot.getValue());
                TextView textView2 = findViewById(R.id.Expiry);
                textView2.setText("Expires in "+e+" days");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TextView textView = findViewById(R.id.title);
        textView.setText(a);

        TextView textView1 = findViewById(R.id.quantity);
        textView1.setText(b+"kgs");
        ImageView imageView=  findViewById(R.id.image);
        Glide.with(dis.this).load(c).into(imageView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dis.this,buy.class);
                i.putExtra("d",d);
                startActivity(i);
            }
        });
    }
}