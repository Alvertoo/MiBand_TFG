package com.tfg.bidas;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Fragment today;
    Fragment beforeDays;
    Fragment moreInfo;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_beforeDays:
                    beforeDays = new BeforeDaysFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,beforeDays).commit();
                    return true;
                case R.id.navigation_today:
                    today = new TodayFragment(false);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,today).commit();
                    return true;
                case R.id.navigation_moreInfo:
                    moreInfo = new MoreInfoFragment(MainActivity.this);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,moreInfo).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_today);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        today = new TodayFragment(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,today).commit();
    }

}
