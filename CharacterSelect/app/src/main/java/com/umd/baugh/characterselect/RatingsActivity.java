package com.umd.baugh.characterselect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class RatingsActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getIntent().getExtras();
        String type = params.getString("characterType");
        setContentView(R.layout.activity_ratings);
        LaunchFragment(params);
    }

    private void LaunchFragment(Bundle params){
        Fragment ratingsFragment = new CharacterRatingsFragment();
        ratingsFragment.setArguments(params);
        FragmentManager fragMngr = getSupportFragmentManager();
        fragMngr.beginTransaction()
                .replace(R.id.ratingsContainer, ratingsFragment)
                .commit();
    }
}
