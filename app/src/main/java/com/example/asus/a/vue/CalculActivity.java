package com.example.asus.a.vue;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.a.R;
import com.example.asus.a.controleur.Controle;
import com.example.asus.a.outils.MesOutils;

public class CalculActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }
    //proprietes

    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Controle controle;
  //  private Button btnCalc;
    /**
     * initialisation des liens avec des objets graphiques
     */
    private void init(){
        txtPoids=(EditText)findViewById(R.id.txtPoids);
        txtTaille=(EditText)findViewById(R.id.txtTaille);
        txtAge=(EditText)findViewById(R.id.txtAge);
        rdHomme=(RadioButton)findViewById(R.id.rdHomme);
        rdFemme=(RadioButton)findViewById(R.id.rdFemme);
        lblIMG=(TextView)findViewById(R.id.lblIMG);
        imgSmiley=(ImageView)findViewById(R.id.imgSmiley);
     //   btnCalc=(Button)findViewById(R.id.btnCalc);
        this.controle=Controle.getInstance(this);

        recupProfil();
        ecouteCalcul();
        ecouteMenu();
    }

    /**
     * Ecoute événement sur Boutton Calcul
     */
    private void ecouteCalcul(){
        ((Button)findViewById(R.id.btnCalc)).setOnClickListener(new Button.OnClickListener(){
                    public void onClick(View v) {
                        // Toast.makeText(CalculActivity.this,"test",Toast.LENGTH_SHORT).show();
                        Integer poids = 0;
                        Integer taille = 0;
                        Integer age = 0;
                        Integer sexe = 0;
                        //recuperation des donnée saisie
                        try {
                            poids = Integer.parseInt(txtPoids.getText().toString());
                            taille = Integer.parseInt(txtTaille.getText().toString());
                            age = Integer.parseInt(txtAge.getText().toString());
                        } catch (Exception e) {
                        }
                        ;
                        if (rdHomme.isChecked()) {
                            sexe = 1;
                        }
                        //controle des donnée saisie

                        if (poids == 0 || taille == 0 || age == 0) {
                            Toast.makeText(CalculActivity.this, "saisie incorrect", Toast.LENGTH_SHORT).show();
                        } else {
                                afficheResult(poids,taille,age,sexe);
                        }
                    }
                });
    }

    /**
     * Affichage de l'IMG, du message et de l'image
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids,Integer taille,Integer age,Integer sexe){
        this.controle.creerProfil(poids,taille,age,sexe,this);
        float img=this.controle.getImg();
        String message=this.controle.getMessage();

        //affichage

        if(message=="normal"){
            imgSmiley.setImageResource(R.drawable.normal);
            lblIMG.setText(String.format("%.01f",img)+ message);
        }

        else{
            lblIMG.setTextColor(Color.RED);
            if(message=="trop faible"){
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setText(String.format("%.01f",img)+ message +"tu dois augmenter ton poids");

            }else if(message=="trop élevé"){
                imgSmiley.setImageResource(R.drawable.maigre);
                lblIMG.setText(String.format("%.01f",img)+ message +"tu dois diminuer ton poids");

            }
            else{
                lblIMG.setTextColor(Color.GREEN);

                imgSmiley.setImageResource(R.drawable.graisse);

             lblIMG.setText(MesOutils.format2Decimal(img)+" : IMG "+message);
            }
        }


    }
    /**
     * récupération du profil s'il a été sérialisé
     */
    public void recupProfil(){

        if(controle.getPoids()!=null){
            txtPoids.setText(controle.getPoids().toString());
            txtTaille.setText(controle.getTaille().toString());
            txtAge.setText(controle.getAge().toString());
            rdFemme.setChecked(true);
            if(controle.getSexe()==1){
                rdHomme.setChecked(true);

            }
            //remettre à la vide le profil
            controle.setProfil(null);


            //simule le clic sur le button calcul
           // ((Button)findViewById(R.id.btnCalc)).performClick();
        }
    }

    private void ecouteMenu(){
        ((ImageButton)findViewById(R.id.btnRetourDeCalcul)).setOnClickListener(new ImageButton.OnClickListener(){
            public void onClick(View v) {

                Intent intent=new Intent(CalculActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

            }
        });
    }

}
