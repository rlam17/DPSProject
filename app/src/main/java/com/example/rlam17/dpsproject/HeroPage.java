package com.example.rlam17.dpsproject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;
import static java.security.AccessController.getContext;


/**
 * Created by rlam17 on 11/14/2016.
 *
 *
 *
 */


public class HeroPage extends AppCompatActivity {


    void doMath(String[] heroDetails){
        // Table columns are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

        TextView summary = (TextView)findViewById(R.id.summary);

        String calculatedDamage = heroDetails[0] + " deals ";

        int lmb1dps = Integer.parseInt(heroDetails[1]) * Integer.parseInt(heroDetails[3]);

        calculatedDamage = calculatedDamage + lmb1dps + " damage per second with their primary weapon.";
        calculatedDamage += "\n\n";

        if(Integer.parseInt(heroDetails[4]) != 0){ //Their rmb does damage.
            if(Double.parseDouble(heroDetails[6]) > 1){ //Their rmb does damage per second and has no cooldown
                double rmbdps = Integer.parseInt(heroDetails[4]) * Double.parseDouble(heroDetails[6]);
                rmbdps = Math.round(rmbdps*100.0)/100.0;
                calculatedDamage = calculatedDamage + "Their right click does " + rmbdps + " damage per second as well.";
            } else { //Their rmb has a cooldown
                calculatedDamage = calculatedDamage + "Their right click does " + heroDetails[4] + " damage on top.";
            }
            calculatedDamage+= "\n\n";
        }

        if(Integer.parseInt(heroDetails[7]) != 0){//Their shift does damage
            if(Double.parseDouble(heroDetails[8]) > 1){ //Their shift does damage per second
                calculatedDamage = calculatedDamage + "Their shift key does " + (
                        Integer.parseInt(heroDetails[7]) * Double.parseDouble(heroDetails[8])) + " damage per second as well.";
            }else{ //Their shift does burst with cooldown
                calculatedDamage = calculatedDamage + "Their shift key does " + heroDetails[7] + " damage as well.";
            }

            calculatedDamage+= "\n\n";
        }

        if(Integer.parseInt(heroDetails[11]) != 0){ //Their Q does damage
            if(Integer.parseInt(heroDetails[12]) > 1){ //Their q does damage per second
                double qdps = Integer.parseInt(heroDetails[4]) * Double.parseDouble(heroDetails[6]);
                qdps = Math.round(qdps*100.0)/100.0;
                calculatedDamage = calculatedDamage + "Their ultimate does " + qdps + " damage per second.";
            } else { //Their Q does burst
                calculatedDamage = calculatedDamage + "Their ultimate " + heroDetails[4] + " burst damage.";
            }
            calculatedDamage+= "\n\n";
        }

        if(Integer.parseInt(heroDetails[16]) != 0){ //They have an alternate weapon
            int lmb2dps = Integer.parseInt(heroDetails[16]) * Integer.parseInt(heroDetails[18]);

            calculatedDamage = calculatedDamage + "Their alternate weapon does " + lmb2dps + " damage per second.";
        }


        summary.setText(calculatedDamage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_page);


        Intent intent = getIntent();
        final String[] heroSelected = intent.getStringArrayExtra("heroChosen");


        setTitle(heroSelected[0]);

        // Table columns are:
        //Heroname lmb1	lmb1pc	lmb1ps	rmb	rmbpc	rmbps	shift	shiftps	e	epc	q	qps	health	armor	shield	lmb2	lmb2pc	lmb2ps

        populateTable(heroSelected);

    }


    void populateTable(final String[] heroListing){
        for(String s : heroListing){
            System.out.println(s);
        }
        TextView has = (TextView)findViewById(R.id.has);
        String hasString = (String)has.getText();

        TextView health = (TextView)findViewById(R.id.health);
        health.setText(heroListing[13]);

        String healthString = (String)health.getText();



        if(!heroListing[14].equals("0")){ //has armor
            hasString = hasString + "/Armor";
            healthString = healthString + "/" + heroListing[14];
        }


        if(!heroListing[15].equals("0")){
            hasString = hasString + "/Shield";
            healthString = healthString + "/" + heroListing[15];
        }

        hasString = hasString + ": ";

        has.setText(hasString);
        health.setText(healthString);


        TextView lmb = (TextView)findViewById(R.id.lmbValue);
        lmb.setText(heroListing[1]);

        /*
        TextView lmb2 = (TextView)findViewById(R.id.lmb2Value);
        if(heroListing[16].equals("0")){
            lmb2.setText(heroListing[16]);
            lmb2.setVisibility(View.VISIBLE);
        }
        */


        TextView rmb = (TextView)findViewById(R.id.rmbValue);
        rmb.setText(heroListing[4]);

        TextView shift = (TextView)findViewById(R.id.shiftValue);
        shift.setText(heroListing[7]);

        TextView e = (TextView)findViewById(R.id.eValue);
        e.setText(heroListing[9]);

        TextView q = (TextView)findViewById(R.id.qValue);
        q.setText(heroListing[11]);

        if(!heroListing[16].equals("0")){
            TextView lmb2label = (TextView)findViewById(R.id.lmb2label);
            TextView lmb2 = (TextView)findViewById(R.id.lmb2Value);
            lmb2.setText(heroListing[16]);
            lmb2.setVisibility(View.VISIBLE);
            lmb2label.setVisibility(View.VISIBLE);
        }


        Button button = (Button)findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(v.getContext(), "The button is active", Toast.LENGTH_SHORT).show();
                Intent editCommence = new Intent(HeroPage.this, HeroEdit.class);
                editCommence.putExtra("editThisHero", heroListing);
                startActivityForResult(editCommence, 1);


            }
        });

        doMath(heroListing);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                //Toast.makeText(getApplicationContext(), "Edit okay!", Toast.LENGTH_SHORT).show();
                String[] updatedHero = data.getStringArrayExtra("updatedHero");
                //for(String s:updatedHero)System.out.println(s);
                populateTable(updatedHero);
            } else {
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, "Something went wrong with hero editting", duration);
                toast.show();
            }
        }
    }
}
