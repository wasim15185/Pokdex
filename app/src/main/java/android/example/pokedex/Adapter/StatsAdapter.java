package android.example.pokedex.Adapter;

import android.content.Context;
import android.example.pokedex.ArraysForListViews.Stat;
import android.example.pokedex.R;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;



public class StatsAdapter extends ArrayAdapter<Stat> {


    public StatsAdapter(@NonNull Context context, ArrayList<Stat> arr) {
        super(context, 0, arr);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;


        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.stat_card, parent, false);
        }

        Stat currentStat = getItem(position);

        // Setting text inside stat catagory
        TextView statCatagory = (TextView) listItemView.findViewById(R.id.stat_catagorty);
        if (currentStat != null) {
            statCatagory.setText(currentStat.getStatName());
        }

        // setting number inside

        TextView statNumberView = (TextView) listItemView.findViewById(R.id.stat_number);
        if (currentStat != null) {
            statNumberView.setText(currentStat.getBaseStat());
        }





        // Finding Linear Layout


        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.linearLayOut);

        if (currentStat.getStatName().equals("hp")) {
            Drawable x = ContextCompat.getDrawable(getContext(), R.drawable.stat_gradient_4);
            linearLayout.setBackground(x);

        }

        if (currentStat.getStatName().equals("attack")) {
            linearLayout.setBackground(getContext().getResources().getDrawable(R.drawable.stat_gradient_2));

        }

        if (currentStat.getStatName().equals("defense")) {
            linearLayout.setBackground(getContext().getResources().getDrawable(R.drawable.stat_gradient_2));
        }

        if (currentStat.getStatName().equals("special-attack")) {
            linearLayout.setBackground(getContext().getResources().getDrawable(R.drawable.stat_gradient_3));
        }

        if (currentStat.getStatName().equals("speed")) {
            linearLayout.setBackground(getContext().getResources().getDrawable(R.drawable.stat_gradient_1));


        }

        if (currentStat.getStatName().equals("special-defense")) {
            linearLayout.setBackground(getContext().getResources().getDrawable(R.drawable.stat_gradient_4));

        }


        return listItemView;
    }
}
