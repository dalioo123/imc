package com.example.asus.a.vue;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.asus.a.R;
import com.example.asus.a.controleur.Controle;
import com.example.asus.a.modele.Profil;
import com.example.asus.a.outils.MesOutils;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {
    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Controle controle;
    private Context contexte;
    public HistoListAdapter(Context contexte,ArrayList<Profil> lesProfils){
        this.lesProfils=lesProfils;
        this.contexte=contexte;
        this.inflater=LayoutInflater.from(contexte);
        this.controle=Controle.getInstance(null);
    }
    /**
     * retourne le nombre de lignes
     * @return
     */
    @Override
    public int getCount() {
        return lesProfils.size();
    }

    /**
     * retourne l'item  de la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return lesProfils.get(i);
    }

    /**
     * retourne un indice  par rapport à la ligne actuelle
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * retourne la ligne (view) formate avec gestion des événements
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //declaration d'un holder
        ViewHolder holder;
        // si la ligne  n'existe pas encore
        if(view == null){
            holder = new ViewHolder();
            // la ligne  est construire  avec un formatage (inflater) relié à layout_liste_histo
            view = inflater.inflate(R.layout.layout_list_histo,null);
            //chaque proprieté du holder  est relié à une proprieté graphique
            holder.txtListDate=(TextView)view.findViewById(R.id.txtListDate);
            holder.txtListIMG=(TextView)view.findViewById(R.id.txtListIMG);
            holder.btnListSuppr=(ImageButton)view.findViewById(R.id.btnListSuppr);
            //affecter le holder à la vue
            view.setTag(holder);

        }else{
            //récupération du holder dans la ligne existante
            holder=(ViewHolder)view.getTag();
        }
        //valorisation du contenu du holder (donc la ligne)
        holder.txtListDate.setText(MesOutils.convertDateToString(lesProfils.get(i).getDateMesure()));
        holder.txtListIMG.setText(MesOutils.format2Decimal(lesProfils.get(i).getImg()));
        holder.btnListSuppr.setTag(i);
        //click sur le croix  pour supprimer  le profil enregistré
        holder.btnListSuppr.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                int position = (int)v.getTag();
                System.out.println("aaaaaaaaaaa"+position);
                Log.d("xxxxxxxxxxxxxxxx "+position,"fffff"+position);
                //demande  de suppression au controleur
                controle.delProfil(lesProfils.get(position));
                //rafraichir la liste
               Log.d("bbbbbb "+lesProfils.get(position).getDateMesure(),"bbbb"+position);

                notifyDataSetChanged();

            }
        });
        //
        holder.txtListDate.setTag(i);
        //click sur le reste de la ligne
        holder.txtListDate.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                int position = (int)v.getTag();
                //demande  de l'affichage  du profil  dans CalculActivity

                ((HistoActivity)contexte).afficheProfil(lesProfils.get(position));


            }
        });

        //
        holder.txtListIMG.setTag(i);
        //click sur le reste de la ligne
        holder.txtListIMG.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                int position = (int)v.getTag();
                //demande  de l'affichage  du profil  dans CalculActivity

                ((HistoActivity)contexte).afficheProfil(lesProfils.get(position));


            }
        });
        return view;
    }
    private class ViewHolder{
        ImageButton btnListSuppr;
        TextView txtListDate;
        TextView txtListIMG;

    }
}
