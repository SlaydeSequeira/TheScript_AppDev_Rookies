package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.thescript.adapter.RecyclerAdapter2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class HomePage2 extends AppCompatActivity {
    String count,temp;
    PieChart pieChart;
    RecyclerView recyclerView ;
    RecyclerAdapter2 adapter;
    int c;
    String[] Item = new String[100];
    String Cost[]= new String[100];
    String Colour[]= {"#FFA726","#AA00FF","#02FFFF","#EF5350","#38F969","#00A726","#fb7268","#024265","#FF3700B3","#FFBB86FC"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
        pieChart = findViewById(R.id.piechart);
        recyclerView = findViewById(R.id.recyclerView);
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference myRef= firebaseDatabase.getReference("market");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count=String.valueOf(snapshot.child("count").getValue());
                c=Integer.parseInt(count);
                pieChart.clearChart();
                for (int i = 0; i < c  ; i++) {
                    temp = String.valueOf(i);
                    Cost[i] = String.valueOf(snapshot.child("cost").child(temp).getValue());
                    Item[i] = String.valueOf(snapshot.child("Title").child(temp).getValue());
                    setData(Cost[i],Item[i],Colour[i]);
                    newView();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void setData(String cost, String item, String color)
    {
        pieChart.addPieSlice(
                new PieModel(
                        item,
                        Integer.parseInt(cost),
                        Color.parseColor(color)));
        pieChart.startAnimation();
    }
    private void newView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(HomePage2.this));
        adapter = new RecyclerAdapter2(HomePage2.this, Item,Colour,c,Cost);
        recyclerView.setAdapter(adapter);
    }

    // Adding Logout Functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){


            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(HomePage2.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            case R.id.sort:
                Intent i = new Intent(HomePage2.this, Donation.class);
                startActivity(i);


        }
        return false;
    }

}