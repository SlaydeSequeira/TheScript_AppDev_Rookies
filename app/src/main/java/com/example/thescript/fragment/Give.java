package com.example.thescript.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thescript.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Give extends Fragment {
EditText editText;
Button button;
String a;
String temp;
int count;
int flag=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_give, container, false);
        editText=view.findViewById(R.id.edit);
        button=view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=1;
                a=editText.getText().toString();
                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference myRef = firebaseDatabase.getReference("Notice");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(flag==1) {
                            temp = String.valueOf(snapshot.child("nc").getValue());
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put(temp, a);
                            myRef.updateChildren(hashMap);
                            flag = 0;
                            count = Integer.parseInt(temp);
                            count++;
                            temp = String.valueOf(count);
                            myRef.child("nc").setValue(temp);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        return view;
    }
}