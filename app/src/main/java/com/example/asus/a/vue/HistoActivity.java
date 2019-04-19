package com.example.asus.a.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.asus.a.R;
import com.example.asus.a.controleur.Controle;
import com.example.asus.a.modele.Profil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {
private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        this.controle = Controle.getInstance(this);
        ecouteRetourMenu();
        creerListe();
    }

    private void ecouteRetourMenu(){
        ((ImageButton)findViewById(R.id.btnRetourDeHisto)).setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v) {

                Intent intent=new Intent(HistoActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

            }
        });
    }

    /**
     * créer la liste adapter
     */
    private void creerListe(){


        ArrayList<Profil> lesProfils = controle.getLesProfils();
        Collections.sort(lesProfils, Collections.<Profil>reverseOrder());
        if(lesProfils != null){
            ListView lstHisto = (ListView)findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(this,lesProfils);
            lstHisto.setAdapter(adapter);
        }
    }

    /**
     * demande d'afficher le profil dans calculActivity
     * @param profil
     */
    public void afficheProfil(Profil profil){
        controle.setProfil(profil);

        Intent intent=new Intent(HistoActivity.this,CalculActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
