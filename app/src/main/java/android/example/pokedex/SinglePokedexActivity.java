package android.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.pokedex.Adapter.StatsAdapter;
import android.example.pokedex.ArraysForListViews.SinglePokeMon;
import android.example.pokedex.ArraysForListViews.Stat;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SinglePokedexActivity extends AppCompatActivity {





    SinglePokeMon pokeMonDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_pokedex);

        Intent reciveIntent = getIntent();
        String url = reciveIntent.getStringExtra("URL");
        String imgUrl = reciveIntent.getStringExtra("ImgUrl");

        try {
            fetchData(url,imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void fetchData(String url,String imgUrl) throws NullPointerException, Exception {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                updateUiInterNetConnection() ;
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {


                    String myResponse = response.body().string();
                    JSONObject jsonObject;

                    try {
                        jsonObject = new JSONObject(myResponse);
                        String height = jsonObject.getString("height");
                        String name = jsonObject.getString("name");
                        String weight = jsonObject.getString("weight");

                        JSONArray stats = jsonObject.getJSONArray("stats");
                        JSONArray types = jsonObject.getJSONArray("types");

                        String type1 = null;
                        String type2 = null;

                        ArrayList<Stat> listOfStats = new ArrayList<>();

                        for (int i = 0; i < types.length(); i++) {
                            JSONObject x = (JSONObject) types.get(i);

                            if (x.getInt("slot") == 1) {
                                type1 = x.getJSONObject("type").getString("name");
                            }

                            if (x.getInt("slot") == 2) {
                                type2 = x.getJSONObject("type").getString("name");
                            }

                        }

                        for (int i = 0; i < stats.length(); i++) {

                            JSONObject particularJsonObj = (JSONObject) stats.get(i);
                            int baseStat = particularJsonObj.getInt("base_stat");
                            String statName = particularJsonObj.getJSONObject("stat").getString("name");
                            listOfStats.add(new Stat(baseStat, statName));


                        }


                        pokeMonDetails = new SinglePokeMon(name, height, weight, type1, type2, listOfStats);


                        SinglePokedexActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateUi(imgUrl);
                            }
                        });


                    } catch (JSONException e) {




                    }


                }


            }
        });

    }


    private void updateUi(String imgUrl) {

        // finding image VIEW with id
        ImageView imgView = (ImageView) findViewById(R.id.imgOfPokeMon);
        // Setting img inside img View
        if (imgUrl != null) {
            Glide.with(this).load(imgUrl).into(imgView);
        }


        // setting Name of Pokemon inside TextView

        TextView pokeMonNameTextView = (TextView) findViewById(R.id.nameOfPokeMon);


        if (pokeMonDetails != null) {
            pokeMonNameTextView.setText(pokeMonDetails.getName());
        }
        // setting Weight of Pokemon inside TextView

        TextView pokeMonWeight = (TextView) findViewById(R.id.pokemonWeight);
        if (pokeMonDetails != null) {
            pokeMonWeight.setText(pokeMonDetails.getWeight());
        }
        // setting Height of Pokemon inside TextView
        TextView pokeMonHeight = (TextView) findViewById(R.id.pokemonHeight);
        if (pokeMonDetails != null) {
            pokeMonHeight.setText(pokeMonDetails.getHeight());
        }

        // setting Type1 of Pokemon inside TextView
        TextView type1_Text_View = (TextView) findViewById(R.id.Type_1_ForPokeMon);
        if (pokeMonDetails != null) {
            type1_Text_View.setText(pokeMonDetails.getType1());
        }
        // setting Typ2 of Pokemon inside TextView
        TextView type2_Text_View = (TextView) findViewById(R.id.Type_2_ForPokeMon);
        if (pokeMonDetails != null) {
            type2_Text_View.setText(pokeMonDetails.getType2());
        }


        if (pokeMonDetails != null) {
            StatsAdapter adapter = new StatsAdapter(this, pokeMonDetails.getListOfStats());

            GridView gridView = (GridView) findViewById(R.id.gridView_single_pokemon);

            gridView.setAdapter(adapter);


        }


    }


    private void updateUiInterNetConnection(){

        setContentView(R.layout.no_internet_connection);

    }



}