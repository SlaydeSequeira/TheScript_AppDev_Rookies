package com.example.thescript;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class dis extends AppCompatActivity {

    String a,b,c,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis);
       Objects.requireNonNull(getSupportActionBar()).hide();
        a=getIntent().getStringExtra("a");
        b=getIntent().getStringExtra("b");
        c=getIntent().getStringExtra("c");
        d=getIntent().getStringExtra("d");
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