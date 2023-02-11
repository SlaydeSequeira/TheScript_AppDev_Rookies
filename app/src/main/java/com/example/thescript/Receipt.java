package com.example.thescript;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Receipt extends AppCompatActivity {
    String a,b,c;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        btn = findViewById(R.id.button);
        a=getIntent().getStringExtra("1");
        b=getIntent().getStringExtra("2");
        c=getIntent().getStringExtra("3");
        TextView textView=findViewById(R.id.text_success_pay);
        textView.setText("Amount Received: "+a+"\n\nUpi id: "+b+"\n\nName of Sender: "+c+"\n\nName of receiver: Naiveda");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Receipt.this,HomePage.class);
                startActivity(i);
            }
        });
    }
}