package android.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.example.pokedex.Adapter.CardAdapter;
import android.example.pokedex.ArraysForListViews.Card;
import android.os.Bundle;


import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private String POKEDEX_URL = "https://pokeapi.co/api/v2/pokemon";
    private String NEXT_POKEDEX_URL;


    CardAdapter adapter;

    //Getting Grid Id
    GridView gridView;

    // Objects Of Img url ;
    ImageUrls img = new ImageUrls();


    final ArrayList<Card> dataArrAbout = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridView = (GridView) findViewById(R.id.gridView);


        try {
            fetchData();
        } catch (Exception e) {

        }

        updateUi();

    }


    private void fetchData() throws NullPointerException, Exception {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(POKEDEX_URL).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }


            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    String myResponse = response.body().string();
                    JSONObject jsonObject;

                    try {
                        jsonObject = new JSONObject(myResponse);
                        String nextUrl = jsonObject.getString("next");
                        NEXT_POKEDEX_URL = nextUrl;


                        JSONArray arrays = jsonObject.getJSONArray("results");

                        String detailUrl;
                        String imgName;

                        JSONObject particularArrObj;
                        for (int i = 0; i < arrays.length(); i++) {

                            particularArrObj = arrays.getJSONObject(i);
                            imgName = particularArrObj.getString("name");

                            detailUrl = particularArrObj.getString("url");


                            dataArrAbout.add(new Card(imgName, detailUrl));


                        }

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                String particularUrl;
                                String imgUrl;

                                try {

                                    for (int i = 0; i < dataArrAbout.size(); i++) {
                                        particularUrl = dataArrAbout.get(i).getParticularPokeMonUrl();
                                        getImageUrls(particularUrl, i);


                                    }

                                } catch (Exception e) {

                                }

                            }
                        });


                    } catch (JSONException | NullPointerException e) {

                    }


                }

            }
        });


    }


    public void getImageUrls(String url, int position) throws NullPointerException {


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myNewResponse = response.body().string();
                    JSONObject myObj;
                    String pngImgUrl;


                    try {
                        myObj = new JSONObject(myNewResponse);
                        JSONObject sprites = myObj.getJSONObject("sprites");
                        JSONObject other = sprites.getJSONObject("other");
                        JSONObject officialArtwork = other.getJSONObject("official-artwork");
                        pngImgUrl = officialArtwork.getString("front_default");

                        img.setPng(pngImgUrl);

                        dataArrAbout.get(position).setImg(img.getPng());


                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                    updateUi();
                                adapter.notifyDataSetChanged();

                            }
                        });

                    } catch (JSONException e) {

                    }

                }


            }

        });


    }


    private void updateUi() {
        //Getting Grid Id

        // Adapters
        adapter = new CardAdapter(this, dataArrAbout);

        gridView.setAdapter(adapter);

        /*
         * @param adapterView --> That simple refers parent view here the parent view is ListView
         * @param view -> listItem which is clicked on but it give the view do not extra data about that word
         * @param i --> it is postion of the item within the adapter's data source
         * @ l --> it is rowId of clicked item .In adapter you can specify the numeric ID to identify each row
         */

        gridView.setOnItemClickListener((AdapterView<?> parent, View view, int i, long l) -> {

                    Intent intent = new Intent(MainActivity.this, SinglePokedexActivity.class);
                    intent.putExtra("URL",dataArrAbout.get(i).getParticularPokeMonUrl()) ;
                    intent.putExtra("ImgUrl",dataArrAbout.get(i).getImg()) ;

                    startActivity(intent);
//                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);

                }
        );


    }


}