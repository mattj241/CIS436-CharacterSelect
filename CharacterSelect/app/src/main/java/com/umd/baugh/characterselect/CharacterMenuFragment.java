package com.umd.baugh.characterselect;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

//This is the main fragment class handler.
// It handles the TextView onClicks differently on smaller screen sizes vs.
// larger screen sizes. More below.
public class CharacterMenuFragment extends Fragment implements View.OnClickListener {
    TextView warrior, healer, mage, hunter, paladin;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.character_menu, container, false);
        warrior = view.findViewById(R.id.WarriorMenuText);
        healer = view.findViewById(R.id.HealerManuText);
        mage = view.findViewById(R.id.MageMenuText);
        hunter = view.findViewById(R.id.HunterMenuText);
        paladin = view.findViewById(R.id.PaladinMenuText);
        warrior.setOnClickListener(this);
        healer.setOnClickListener(this);
        mage.setOnClickListener(this);
        hunter.setOnClickListener(this);
        paladin.setOnClickListener(this);
        return view;
    }

    //Throughout this switch case:
    // IF (large screen size)
    // THEN handle fragment switching in main activity
    // ELSE a new intent and activity is launched
    @Override
    public void onClick(View v) {
        boolean isTablet = false;
        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE){
            isTablet = true;
        }
        Intent intent = new Intent(view.getContext(), RatingsActivity.class);
        Bundle params = new Bundle();
        switch (v.getId()) {
            case R.id.WarriorMenuText:
                if (isTablet){
                    ((MainActivity)getActivity()).handleTabletFragmentSwitches("Warrior");
                }
                else {
                    params.putString("characterType", "Warrior");
                    intent.putExtras(params);
                    startActivity(intent);
                }
                break;
            case R.id.HealerManuText:
                if (isTablet){
                    ((MainActivity)getActivity()).handleTabletFragmentSwitches("Healer");
                }
                else {
                    params.putString("characterType", "Healer");
                    intent.putExtras(params);
                    startActivity(intent);
                }
                break;
            case R.id.MageMenuText:
                if (isTablet){
                    ((MainActivity)getActivity()).handleTabletFragmentSwitches("Mage");
                }
                else {
                    params.putString("characterType", "Mage");
                    intent.putExtras(params);
                    startActivity(intent);
                }
                break;
            case R.id.HunterMenuText:
                if (isTablet){
                    ((MainActivity)getActivity()).handleTabletFragmentSwitches("Hunter");
                }
                else {
                    params.putString("characterType", "Hunter");
                    intent.putExtras(params);
                    startActivity(intent);
                }
                break;
            case R.id.PaladinMenuText:
                if (isTablet){
                    ((MainActivity)getActivity()).handleTabletFragmentSwitches("Paladin");
                }
                else {
                    params.putString("characterType", "Paladin");
                    intent.putExtras(params);
                    startActivity(intent);
                }
                break;
            default:
                Toast.makeText(view.getContext(), "Uh oh", Toast.LENGTH_SHORT);
        }
    }
}