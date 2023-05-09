package com.example.gre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.gre.Fragments.FirstFragment;
import com.example.gre.Fragments.SecondFragment;
import com.example.gre.Fragments.ThirdFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView;

                bottomNavigationView = findViewById(R.id.bottomNavigationView);

                bottomNavigationView.setOnNavigationItemSelectedListener(MainActivity.this);
                bottomNavigationView.setSelectedItemId(R.id.check);

            }
            FirstFragment firstFragment = new FirstFragment();
            SecondFragment secondFragment = new SecondFragment();
            ThirdFragment thirdFragment = new ThirdFragment();


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.check:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                return true;

            case R.id.wrong:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;
        }
        return false;
    }
}

