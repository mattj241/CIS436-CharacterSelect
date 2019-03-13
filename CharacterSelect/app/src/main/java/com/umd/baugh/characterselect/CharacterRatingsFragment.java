package com.umd.baugh.characterselect;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CharacterRatingsFragment extends Fragment implements RatingBar.OnRatingBarChangeListener {
    RatingBar strength, intellect, wisdom, dexterity;
    TextView Title;
    String CharacterName;
    String appName = "com.umd.baugh.characterselect",
            warrior = "warrior", mage = "mage", healer = "healer",
            hunter = "hunter", paladin = "paladin", strengthString = "strength",
            intellectString = "intellect", wisdomString = "wisdom", dexterityString = "dexterity";
    SharedPreferences prefs;
    int pointsUsed = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        prefs = ((MainActivity)getActivity()).getSharedPreferences(
                 appName, Context.MODE_PRIVATE);
        CharacterName = this.getArguments().getString("characterType");
        View view = inflater.inflate(R.layout.ratings_page, container, false);
        Title = view.findViewById(R.id.CharatcterTitle);
        Title.setText(CharacterName);
        strength = view.findViewById(R.id.StrengthRtgBar);
        intellect = view.findViewById(R.id.IntellectRtgBar);
        wisdom = view.findViewById(R.id.WisdomRtgBar);
        dexterity = view.findViewById(R.id.DexterityRtgBar);
        strength.setOnRatingBarChangeListener(this);
        intellect.setOnRatingBarChangeListener(this);
        wisdom.setOnRatingBarChangeListener(this);
        dexterity.setOnRatingBarChangeListener(this);
        return view;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        String key = null;
        int newStarAmount;
        switch (ratingBar.getId()){
            case R.id.StrengthRtgBar:
                key = String.format("%s/%s", CharacterName, strengthString);
                break;
            case R.id.WisdomRtgBar:
                key = String.format("%s/%s", CharacterName, wisdomString);
                break;
            case R.id.IntellectRtgBar:
                key = String.format("%s/%s", CharacterName, intellectString);
                break;
            case R.id.DexterityRtgBar:
                key = String.format("%s/%s", CharacterName, dexterityString);
                break;
            default:
                Toast.makeText(this.getContext(), "Uh-oh", Toast.LENGTH_SHORT).show();
                break;
        }
        newStarAmount = ratingBar.getNumStars();
        prefs.edit().putInt(key, newStarAmount).apply();
        
    }
}
