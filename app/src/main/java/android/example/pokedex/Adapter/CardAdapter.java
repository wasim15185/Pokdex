package android.example.pokedex.Adapter;

import android.content.Context;
import android.example.pokedex.ArraysForListViews.Card;
import android.example.pokedex.R;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card> {

  public CardAdapter(@NonNull Context context, ArrayList<Card> cardArr ){
      super(context,0,cardArr);

  }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {




        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_main_activity, parent, false);
        }

        Card currentCard = getItem(position);


        // Setting ImageView in Image
        ImageView imgView =(ImageView) listItemView.findViewById(R.id.imgForPokedex) ;

        if(currentCard.getImg() != null){

            Glide.with(getContext()).load(currentCard.getImg()).into(imgView);

        }


        // Setting Text in Text
        TextView textView = (TextView) listItemView.findViewById(R.id.textV);


        textView.setText(currentCard.getNameOfPokeMon());


        return listItemView;
    }



}
