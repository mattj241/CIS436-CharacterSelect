package com.umd.baugh.characterselect;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/*
 * Author: Matthew London
 * Professor: John P. Baugh
 * Class: CIS 436
 * Project: Character Select - Project 2
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchMainFragment();
    }

    //Automatically call the main fragment in the main activity, not dependent on screen size
    public void launchMainFragment() {
        CharacterMenuFragment firstFragment = new CharacterMenuFragment();
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.containerLayout, firstFragment).commit();
        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {
            handleTabletFragmentSwitches("Warrior");
        }
    }

    //Only on Tablets do we load both fragments in the Main activity.
    //This called if the screen resources are bigger than "SCREENLAYOUT_SIZE_LARGE"
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
