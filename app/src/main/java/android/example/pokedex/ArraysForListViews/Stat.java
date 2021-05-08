package android.example.pokedex.ArraysForListViews;

public class Stat {
    /**
     * mBaseStat is base stat of a Pokemon
     */
    private int mBaseStat ;
    /**
     * mStatName is stat name of Pokemon
     */
    private String mStatName ;

    /**
     * {@link Stat} is a constractor
     * @param baseStat
     * @param statName
     */

    public Stat(int baseStat,String statName){
        mBaseStat=baseStat;
        mStatName=statName ;
    }

    /**
     * getBaseStat is getting for  getBaseStat
     * @return convertedString
     */
    public String getBaseStat() {
        String convertedString = String.valueOf(mBaseStat) ;
        return convertedString;
    }

    /**
     * getStatName is getting for StatName
     * @return
     */
    public String getStatName() {
        return mStatName;
    }
}
