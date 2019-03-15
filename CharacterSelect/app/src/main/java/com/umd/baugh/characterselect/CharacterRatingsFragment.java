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

//Heavy amount of variables indicate the amount of preferences that must be stored here.
//There is a "last known value" and "newly known value" for every stat. The last known value
//is loaded upon every fragment onCreateView/onResume. All preferences are written in the onStop
//
//If the a stat gets a new rating, and said rating sets total above 10 points,
// the stat is reset to the last known value
public class CharacterRatingsFragment extends Fragment implements RatingBar.OnRatingBarChangeListener {
    RatingBar strength, intellect, wisdom, dexterity;
    TextView Title, PointsText;
    String CharacterName;
    String appName = "com.umd.baugh.characterselect", strengthString = "strength",
            intellectString = "intellect", wisdomString = "wisdom", dexterityString = "dexterity";
    SharedPreferences prefs;
    static int MAX_POINTS= 10;
    int lastStrengthRating, newStrengthRating,
        lastWisdomRating, newWisdomRating,
        lastIntellectRating, newIntellectRating,
        lastDexterityRating, newDexterityRating;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CharacterName = this.getArguments().getString("characterType");
        prefs = getActivity().getSharedPreferences(appName, Context.MODE_PRIVATE);
        lastStrengthRating = prefs.getInt(String.format("%s/%s", CharacterName, strengthString), 0);
        lastWisdomRating = prefs.getInt(String.format("%s/%s", CharacterName, wisdomString), 0);
        lastIntellectRating = prefs.getInt(String.format("%s/%s", CharacterName, intellectString), 0);
        lastDexterityRating = prefs.getInt(String.format("%s/%s", CharacterName, dexterityString), 0);
        int total = getRatingsTotal(lastStrengthRating, lastWisdomRating, lastIntellectRating, lastDexterityRating);


        View view = inflater.inflate(R.layout.ratings_page, container, false);
        Title = view.findViewById(R.id.CharatcterTitle);
        Title.setText(CharacterName);
        PointsText = view.findViewById(R.id.PointsText);
        PointsText.setText(String.valueOf(MAX_POINTS - total));

        strength = view.findViewById(R.id.StrengthRtgBar);
        intellect = view.findViewById(R.id.IntellectRtgBar);
        wisdom = view.findViewById(R.id.WisdomRtgBar);
        dexterity = view.findViewById(R.id.DexterityRtgBar);

        strength.setRating(lastStrengthRating);
        intellect.setRating(lastIntellectRating);
        wisdom.setRating(lastWisdomRating);
        dexterity.setRating(lastDexterityRating);

        strength.setOnRatingBarChangeListener(this);
        intellect.setOnRatingBarChangeListener(this);
        wisdom.setOnRatingBarChangeListener(this);
        dexterity.setOnRatingBarChangeListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        lastStrengthRating = prefs.getInt(String.format("%s/%s", CharacterName, strengthString), 0);
        lastWisdomRating = prefs.getInt(String.format("%s/%s", CharacterName, wisdomString), 0);
        lastIntellectRating = prefs.getInt(String.format("%s/%s", CharacterName, intellectString), 0);
        lastDexterityRating = prefs.getInt(String.format("%s/%s", CharacterName, dexterityString), 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.edit().putInt(String.format("%s/%s", CharacterName, strengthString), lastStrengthRating).commit();
        prefs.edit().putInt(String.format("%s/%s", CharacterName, wisdomString), lastWisdomRating).commit();
        prefs.edit().putInt(String.format("%s/%s", CharacterName, intellectString), lastIntellectRating).commit();
        prefs.edit().putInt(String.format("%s/%s", CharacterName, dexterityString), lastDexterityRating).commit();
    }

    public int getRatingsTotal(int strength, int wisdom, int intellect, int dexterity){
        int total = strength + wisdom + intellect + dexterity;
        return total;
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        String key = null;
        int localTotal = 0;
        int newStarAmount = Math.round(ratingBar.getRating());
        switch (ratingBar.getId()){
            case R.id.StrengthRtgBar:
                newStrengthRating = newStarAmount;
                localTotal = getRatingsTotal(newStrengthRating, lastWisdomRating,
                        lastIntellectRating, lastDexterityRating);
                if (localTotal > MAX_POINTS){
                    Toast.makeText(this.getContext(),
                            String.format("The %s rating exceeds the stat limit", strengthString),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    lastStrengthRating = newStrengthRating;
                }
                localTotal = getRatingsTotal(lastStrengthRating, lastWisdomRating,
                        lastIntellectRating, lastDexterityRating);
                ratingBar.setRating(lastStrengthRating);
                key = String.format("%s/%s", CharacterName, strengthString);
                break;
            case R.id.WisdomRtgBar:
                newWisdomRating = newStarAmount;
                localTotal = getRatingsTotal(lastStrengthRating, newWisdomRating,
                        lastIntellectRating, lastDexterityRating);
                if (localTotal > MAX_POINTS){
                    Toast.makeText(this.getContext(),
                            String.format("The %s rating exceeds the stat limit", wisdomString),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    lastWisdomRating = newWisdomRating;
                }
                localTotal = getRatingsTotal(lastStrengthRating, lastWisdomRating,
                        lastIntellectRating, lastDexterityRating);
                ratingBar.setRating(lastWisdomRating);
                key = String.format("%s/%s", CharacterName, wisdomString);
                break;
            case R.id.IntellectRtgBar:
                newIntellectRating = newStarAmount;
                localTotal = getRatingsTotal(lastStrengthRating, lastWisdomRating,
                        newIntellectRating, lastDexterityRating);
                if (localTotal > MAX_POINTS){
                    Toast.makeText(this.getContext(),
                            String.format("The %s rating exceeds the stat limit", intellectString),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    lastIntellectRating = newIntellectRating;
                }
                localTotal = getRatingsTotal(lastStrengthRating, lastWisdomRating,
                        lastIntellectRating, lastDexterityRating);
                ratingBar.setRating(lastIntellectRating);
                key = String.format("%s/%s", CharacterName, intellectString);
                break;
            case R.id.DexterityRtgBar:
                newDexterityRating = newStarAmount;
                localTotal = getRatingsTotal(lastStrengthRating, lastWisdomRating,
                        lastIntellectRating, newDexterityRating);
                if (localTotal > MAX_POINTS){
                    Toast.makeText(this.getContext(),
                            String.format("The %s rating exceeds the stat limit", dexterityString),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    lastDexterityRating = newDexterityRating;
                }
                localTotal = getRatingsTotal(lastStrengthRating, lastWisdomRating,
                        lastIntellectRating, lastDexterityRating);
                ratingBar.setRating(lastDexterityRating);
                key = String.format("%s/%s", CharacterName, dexterityString);
                break;
            default:
                Toast.makeText(this.getContext(), "Uh-oh", Toast.LENGTH_LONG).show();
                break;
        }
        //prefs.edit().putInt(key, newStarAmount).apply();
        String newTotal = String.valueOf(MAX_POINTS - localTotal);
        PointsText.setText(newTotal);
    }
}
