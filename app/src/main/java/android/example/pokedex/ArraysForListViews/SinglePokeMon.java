package android.example.pokedex.ArraysForListViews;

import java.util.ArrayList;
import java.util.List;

public class SinglePokeMon {

    /**mHeight is Height of Pokemon
     *
     */
    private String mHeight ;
    /**
     * mName is Name of the Pokemon
     */
    private String mName ;

    /**
     * mWeight is weight of Pokemon
     */
    private String mWeight;

    /**
     * mType1 is Type of pokeMon
     */
    private String mType1 ;

    /**
     * mType2 is Another Type of pokeMon
     */

    private String mType2 ;

    /**
     * listOfStats is List of Stats
     */
    private ArrayList<Stat> mListOfStats ;

    /**
     *  Constractor {@link  SinglePokeMon}
     * @param name
     * @param height
     * @param weight
     * @param listOfStats
     */
    public SinglePokeMon( String name  , String height ,String weight,String type1 ,String type2,ArrayList<Stat> listOfStats ){

        mName=name ;
        mHeight=height;
        mWeight=weight ;
        mType1=type1;
        mType2=type2;
        mListOfStats=listOfStats;

    }



    /**
     * Getting Height of PokeMon
     * @return mHeight
     */
    public String getHeight() {
        return mHeight+" M";
    }

    /**
     * Getting Weight of PokeMon
     * @return mWeight
     */

    public String getWeight() {
        return mWeight+ " KG";
    }

    /**
     * Getting Name of PokeMon
     * @return mName
     */
    public String getName() {
        return mName;
    }
    /**
     * Getting Type 1 of PokeMon
     * @return mType1
     */

    public String getType1() {
        return mType1;
    }

    /**
     * Getting Type 2 of PokeMon
     * @return mType2
     */
    public String getType2() {
        return mType2;
    }

    /**
     * Getting list of Stats of Pokemon
     * @return mListOfStats
     */
    public ArrayList<Stat> getListOfStats() {
        return mListOfStats;
    }
}
