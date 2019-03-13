package com.umd.baugh.characterselect;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class CharacterRatingsFragment extends Fragment {
    RatingBar strength, intellect, wisdom, dexterity;
    TextView Title;
    String CharacterName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CharacterName = this.getArguments().getString("characterType");
        View view = inflater.inflate(R.layout.ratings_page, container, false);
        Title = view.findViewById(R.id.CharatcterTitle);
        Title.setText(CharacterName);
        strength = view.findViewById(R.id.StrengthRtgBar);
        intellect = view.findViewById(R.id.IntellectRtgBar);
        wisdom = view.findViewById(R.id.WisdomRtgBar);
        dexterity = view.findViewById(R.id.DexterityRtgBar);
        return view;
    }
}
