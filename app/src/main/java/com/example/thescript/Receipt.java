package com.example.thescript;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Receipt extends AppCompatActivity {
String a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        a=getIntent().getStringExtra("1");
        b=getIntent().getStringExtra("2");
        c=getIntent().getStringExtra("3");
        TextView textView=findViewById(R.id.text_success_pay);
        textView.setText("Amount Received: "+a+"\nUpi id: "+b+"\nName of Sender: "+c+"\nName of receiver: Naiveda");
    }
}