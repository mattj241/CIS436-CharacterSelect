package com.umd.baugh.characterselect;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //preferences.
        setContentView(R.layout.activity_main);
        LaunchFragment();
    }

    public void LaunchFragment() {
        CharacterMenuFragment firstFragment = new CharacterMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.containerLayout, firstFragment).commit();
        Fragment test = fm.findFragmentById(R.id.containerLayout);
    }
}
