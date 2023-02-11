package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class buy extends AppCompatActivity {
    TextView text;
    Button btn;
String d,decrease,value,new1;
int one, two,new2,flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        d=getIntent().getStringExtra("d");
        text= findViewById(R.id.edittext);
        btn= findViewById(R.id.loginbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                decrease=text.getText().toString();
                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference databaseReference= firebaseDatabase.getReference("market").child("cost").child(d);
                if(flag==1) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(flag==1) {
                                value = String.valueOf(snapshot.getValue());
                                one = Integer.parseInt(value);
                                two = Integer.parseInt(decrease);
                                if(two<=one)
                                {
                                    new2 = one - two;
                                    new1 = String.valueOf(new2);
                                    update(new1);
                                    openAct();
                                }
                                else
                                {
                                    Toast.makeText(buy.this, "Insufficient Products", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }

    private void openAct() {
        Intent i = new Intent(buy.this,HomePage1.class);
        startActivity(i);
    }

    private void update(String new1) {
        flag=0;
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference databaseReference= firebaseDatabase.getReference("market").child("cost").child(d);
        databaseReference.setValue(new1);
    }
}