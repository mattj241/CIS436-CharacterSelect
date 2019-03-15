package com.umd.baugh.characterselect;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

//This activity is only called on smaller screen sizes.
// It only uses the ratings fragment.
public class RatingsActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle params = getIntent().getExtras();
        String type = params.getString("characterType");
        setContentView(R.layout.activity_ratings);
        launchRatingsFragment(params);
    }

    private void launchRatingsFragment(Bundle params){
        Fragment ratingsFragment = new CharacterRatingsFragment();
        ratingsFragment.setArguments(params);
        FragmentManager fragMngr = getSupportFragmentManager();
        fragMngr.beginTransaction()
                .replace(R.id.ratingsContainer, ratingsFragment)
                .commit();
    }
}
