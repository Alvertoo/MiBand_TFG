package com.tfg.bidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class MoreInfoFragment extends Fragment {

    private Context context;

    public MoreInfoFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more_info,container,false);
        initCards(view);
        return view;
    }

    private void initCards(View view){
        CardView card1 = view.findViewById(R.id.cardview1);
        CardView card2 = view.findViewById(R.id.cardview2);
        CardView card3 = view.findViewById(R.id.cardview3);
        CardView card4 = view.findViewById(R.id.cardview4);
        CardView card5 = view.findViewById(R.id.cardview5);
        CardView card6 = view.findViewById(R.id.cardview6);
        CardView card7 = view.findViewById(R.id.cardview7);
        CardView card8 = view.findViewById(R.id.cardview8);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, InitPage.class));
            }
        });
    }

}
