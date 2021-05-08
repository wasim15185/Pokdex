package android.example.pokedex.ArraysForListViews;


import android.graphics.Bitmap;

public class Card {

    /**
     * mImageUrl is url for Card .
     */
    private String mImg ;

    /**
     *  mNameOfPokeMon is Name for Card
     */

    private String mNameOfPokeMon ;

    /**
     *  mParticularPokeMonUrl is a particular url for Card
     */


    private String mParticularPokeMonUrl ;


    /**
     * @param nameOfPokeMon is name Of PokeMon
     * @param img is img of Pokemon
     * @param particularPokeMonUrl is Particular URL of Particular PokeMon
     * {@link Card} is a constractor
     */


//  public Card(String nameOfPokeMon,String img , String particularPokeMonUrl){
//
//      mNameOfPokeMon=nameOfPokeMon;
//      mImg=img;
//      mParticularPokeMonUrl=particularPokeMonUrl;
//  }


    /**
     * DOUBLE Constractor
     * @param nameOfPokeMon
     */
    public Card(String nameOfPokeMon ,String particularPokeMonUrl ){

        mNameOfPokeMon=nameOfPokeMon;
        mParticularPokeMonUrl=particularPokeMonUrl ;

    }


    /**
     * method for getNameOfPokeMon
     * @return mNameOfPokeMon
     */

    public String getNameOfPokeMon() {
        return mNameOfPokeMon;
    }
    public String getImg(){return mImg ;}
    public String getParticularPokeMonUrl(){return mParticularPokeMonUrl;}

    public void setImg(String img){
        mImg=img ;
    }

}
