package com.example.asus.a.outils;

import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;
import org.apache.http.NameValuePair;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.logging.LoggingMXBean;

public class AccesHTTP extends AsyncTask<String,Integer,Long> {
private ArrayList<NameValuePair> parametres;
private String ret=null;
public AsyncResponse delegate=null;

    /**
     * constructeur
     */
    public AccesHTTP(){
        parametres = new ArrayList<NameValuePair>();
    }

    /**
     * Ajout d'un paramétre post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom,String valeur){
        parametres.add(new BasicNameValuePair(nom,valeur));
    }

    /**
     * Connexion en tache de fond dans un thread séparé
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp=new DefaultHttpClient();
        HttpPost paramCnx=new HttpPost(strings[0]);
        try {
            //encodage des paramétres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //connexion et envoie de paramétre , attend de reponse
            HttpResponse reponse = cnxHttp.execute(paramCnx);
            //transformation de la reponse
            ret=EntityUtils.toString(reponse.getEntity());

        }catch(UnsupportedEncodingException e){
            Log.d("Erreur encodage","*********"+e.toString());
        }catch(ClientProtocolException e){
            Log.d("Erreur Protocole","*********"+e.toString());
        }catch(IOException e){
            Log.d("Erreur I/O","***********"+e.toString());
        }
        return null;
    }
    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish((ret.toString()));
    }
}
