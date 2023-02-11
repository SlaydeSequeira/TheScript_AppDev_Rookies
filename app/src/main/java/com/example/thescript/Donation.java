package com.example.thescript;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Donation extends AppCompatActivity {

    TextView t1,t2,t3;
    String s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);
        Button button = findViewById(R.id.google_pay_button);
        t1=findViewById(R.id.amount_input);
        t2=findViewById(R.id.upi_id_input);
        t3=findViewById(R.id.name_input);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                s3=t3.getText().toString();
                Intent i = new Intent(Donation.this,Receipt.class);
                i.putExtra("1",s1);
                i.putExtra("2",s2);
                i.putExtra("3",s3);
                startActivity(i);
            }
        });
    }
}