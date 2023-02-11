package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Middle extends AppCompatActivity {
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle);
        Objects.requireNonNull(getSupportActionBar()).hide();
        FirebaseUser firebaseUser;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = firebaseUser.getUid();
        MyRunnable myRunnable= new MyRunnable();
        Thread thread = new Thread(myRunnable);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("MyUsers").child(userid).child("role");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String role =String.valueOf(snapshot.getValue());
                int r=Integer.parseInt(role);
                if(r==2)
                {
                    i = new Intent(Middle.this,HomePage2.class);
                    startActivity(i);
                }
                else if(r==1)
                {
                     i = new Intent(Middle.this,HomePage1.class);
                    startActivity(i);
                }
                else
                {
                     i = new Intent(Middle.this,HomePage.class);
                    startActivity(i);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
      //  thread.start();
    }
    class MyRunnable implements Runnable{
        public void run(){
            try {
                Thread.sleep(5500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(Middle.this,HomePage.class);
            startActivity(i);

        }
    }

}