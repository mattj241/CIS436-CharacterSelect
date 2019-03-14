package com.umd.baugh.characterselect;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LaunchFragment();
    }

    public void LaunchFragment() {
        CharacterMenuFragment firstFragment = new CharacterMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.containerLayout, firstFragment).commit();
        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            handleTabletFragmentSwitches("Warrior");
        }
    }

    public void handleTabletFragmentSwitches(String characterType) {
        Bundle params = new Bundle();
        switch (characterType) {
            case "Warrior":
                params.putString("characterType", "Warrior");
                break;
            case "Healer":
                params.putString("characterType", "Healer");
                break;
            case "Mage":
                params.putString("characterType", "Mage");
                break;
            case "Hunter":
                params.putString("characterType", "Hunter");
                break;
            case "Paladin":
                params.putString("characterType", "Paladin");
                break;
            default:
                Toast.makeText(this, "Uh oh", Toast.LENGTH_SHORT);
        }
        FragmentManager fm = getSupportFragmentManager();
        CharacterRatingsFragment ratingsFragment = new CharacterRatingsFragment();
        ratingsFragment.setArguments(params);
        FragmentManager fragMngr = getSupportFragmentManager();
        fragMngr.beginTransaction()
                .replace(R.id.ratingsContainer, ratingsFragment)
                .commit();
        fm.beginTransaction().add(R.id.ratingsContainer, ratingsFragment);
    }


}
