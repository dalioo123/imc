package com.example.asus.a.outils;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class MesOutils {


   /* public static Date convertStringToDate(String uneDate){
        String formatAttendu="EEE MMM dd hh:mm:ss 'GMT' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        }catch (ParseException e){
            Log.d("erreur","***********"+e.toString());
        }
        return null;
    }  */
   /**
     * Conversion
     * @param uneDate
     * @param formatAttendu
     * @return
     */
    public static Date converStringToDate(String uneDate,String formatAttendu){
        SimpleDateFormat formatter = new SimpleDateFormat(formatAttendu);
        try {
            Date date = formatter.parse(uneDate);
            return date;
        }catch (ParseException e){
            Log.d("erreur","***********"+e.toString());
        }
        return null;
    }

    /**
     * Converion d'une date en chaine sous la forme yyyy-MM-dd hh:mm:ss
     * @param uneDate
     * @return
     */
    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return date.format(uneDate);
    }

    /**
     * retourne un float au format avec 1 chiffre  aprés la virgule
     * @param valeur
     * @return
     */
    public static String format2Decimal(Float valeur){
        return String.format("%.01f",valeur);
    }


}
