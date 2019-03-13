package com.umd.baugh.characterselect;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(view.getContext(), RatingsActivity.class);
        Bundle params = new Bundle();
        switch (v.getId()){
            case R.id.WarriorMenuText:
                params.putString("characterType", "Warrior");
                intent.putExtras(params);
                startActivity(intent);
                break;
            case R.id.HealerManuText:
                params.putString("characterType", "Healer");
                intent.putExtras(params);
                startActivity(intent);
                break;
            case R.id.MageMenuText:
                params.putString("characterType", "Mage");
                intent.putExtras(params);
                startActivity(intent);
                break;
            case R.id.HunterMenuText:
                params.putString("characterType", "Hunter");
                intent.putExtras(params);
                startActivity(intent);
                break;
            case R.id.PaladinMenuText:
                params.putString("characterType", "Paladin");
                intent.putExtras(params);
                startActivity(intent);
                break;
            default:
                Toast.makeText(view.getContext(), "Uh oh", Toast.LENGTH_SHORT);
        }

    }
}
