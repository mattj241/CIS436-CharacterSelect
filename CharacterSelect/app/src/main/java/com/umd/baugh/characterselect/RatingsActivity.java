package com.umd.baugh.characterselect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class RatingsActivity extends AppCompatActivity {

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
                .add(R.id.ratingsContainer, ratingsFragment)
                .commit();
    }

//    Bundle bundle = new Bundle();
//    String myMessage = "Stackoverflow is cool!";
//bundle.putString("message", myMessage );
//    FragmentClass fragInfo = new FragmentClass();
//fragInfo.setArguments(bundle);
//transaction.replace(R.id.fragment_single, fragInfo);
//transaction.commit();
}
