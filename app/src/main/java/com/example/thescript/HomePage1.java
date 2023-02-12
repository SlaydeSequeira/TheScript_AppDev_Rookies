package com.example.thescript;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.thescript.adapter.MyPagerAdapter;
import com.example.thescript.fragment.FoodFragment;
import com.example.thescript.fragment.ProfileFragment;
import com.example.thescript.fragment.SearchFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage1 extends AppCompatActivity {

    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page1);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager= findViewById(R.id.viewpager);
        MyPagerAdapter pagerAdapter= new MyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        pagerAdapter.addFragment(new FoodFragment(),"Food");
        pagerAdapter.addFragment(new ProfileFragment(),"Profile");
        pagerAdapter.addFragment(new SearchFragment(),"Chats");
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_food);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_person_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_message_24);
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
                startActivity(new Intent(HomePage1.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
            case R.id.sort:
                Intent i = new Intent(HomePage1.this,Donation.class);
                startActivity(i);
                return true;
            case R.id.donation:
                Intent i3 = new Intent(HomePage1.this,Donation.class);
                startActivity(i3);
                return true;
            case R.id.notice:
                Intent i2 = new Intent(HomePage1.this,Notice.class);
                startActivity(i2);
                return true;




        }
        return false;
    }

    private void replace (Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.test,fragment);
        fragmentTransaction.commit();
    }

}